package com.capstone.network.service; 
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.capstone.network.DTO.ForwardingRequest;
import com.capstone.network.entities.Incident;
import com.capstone.network.exception.ConflictException;
import com.capstone.network.repository.IncidentRepository;
import com.capstone.network.service.EmailServiceImpl;


@Service
public class IncidentService {
	@Autowired
	private IncidentRepository incidentRepository;
	@Autowired
	private EmailServiceImpl emailService;
	private static final Map<String, Integer> PRIORITY_SLA_HOURS = new HashMap<>();
	private static final Map<String, Integer> SEVERITY_MULTIPLIER = new HashMap<>();

	static { 
		PRIORITY_SLA_HOURS.put("high", 2);
		PRIORITY_SLA_HOURS.put("medium", 36);
		PRIORITY_SLA_HOURS.put("low", 48);
		SEVERITY_MULTIPLIER.put("high", 2);
		SEVERITY_MULTIPLIER.put("medium", 1);
		SEVERITY_MULTIPLIER.put("low", 0);
	} 
	public Incident saveforwardIncident(Incident incident)
	{
		
		incidentRepository.save(incident);
		return incident;
	}
 
	public Incident saveIncident(Incident incident) { 

		String issueType = incident.getIssueType();
		String assignmentGroup = determineAssignmentGroup(issueType);
		incident.setAssignmentGroup(assignmentGroup);
		incident.setForwarded(false);
		incident.setStatus("New");
		incident.setPriority(incident.getPriority());
		incident.setSeverity(incident.getSeverity());
		incident.setUser(incident.getUser());
		incident.setUserEmailId(incident.getUserEmailId());
		LocalDateTime now = LocalDateTime.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);
		incident.setCreatedTime(formattedDateTime);
		startSLA(incident);
		incidentRepository.save(incident);
		System.out.println("id"+incident.getId());
		emailService.sendAcknoweledgment(incident.getId(),incident.getUserEmailId());  
		return incident;
	}  

	
	public String determineAssignmentGroup(String issueType) {
		Map<String, String> assignmentGroupMap = new HashMap<>();
		assignmentGroupMap.put("hardware", "HardwareGroup");
		assignmentGroupMap.put("software", "SoftwareGroup");
		assignmentGroupMap.put("Connectivity Support", "Connectivity Issues");
		assignmentGroupMap.put("Security Issues", "Security Operations Center"); 
		return assignmentGroupMap.getOrDefault(issueType, "DefaultGroup");
	}
	public void startSLA(Incident incident) {  
		incident.setSlaStartTime(LocalDateTime.now());
		calculateSLAEndTime(incident);
		incident.setSlaBreached(false);
	} 
 
	private void calculateSLAEndTime(Incident incident) {
		String priority = incident.getPriority().toLowerCase();
		String severity = incident.getSeverity().toLowerCase();

		int slaHours = PRIORITY_SLA_HOURS.getOrDefault(priority, PRIORITY_SLA_HOURS.get("low"));
		int severityMultiplier = SEVERITY_MULTIPLIER.getOrDefault(severity, SEVERITY_MULTIPLIER.get("medium"));
		slaHours *= severityMultiplier;
		incident.setSlaEndTime(incident.getSlaStartTime().plusHours(slaHours));
	}


	public void updateSLAStatus(Incident incident) { 
		if (LocalDateTime.now().isAfter(incident.getSlaEndTime()) && !incident.isSlaBreached() ) {
			incident.setSlaBreached(true);

			emailService.sendSLAMail(incident.getId(),incident.getUserEmailId() );
		}
	}

	@Scheduled(fixedRate = 3600000)
	public void updateSLAStatusForAllIncidents() {
		List<Incident> allIncidents = incidentRepository.findAll();
		for (Incident incident : allIncidents) {
			updateSLAStatus(incident);
			incidentRepository.save(incident);
		}
	}

	public List<Incident> getIncidentsByAssignmentGroup(String assignmentGroup) {

		return incidentRepository.findByAssignmentGroup(assignmentGroup);
	}  
	public Optional<Incident> findById(Long id) {
		return incidentRepository.findById(id);
	} 
	public List<Incident> getIncidentsByUser(String userName) {
		return incidentRepository.findByUser(userName);
	}

	public List<Incident> getForwardedIncidents(String group) {
		return incidentRepository.findByForwardedToAndForwardedIsTrue(group);
	} 
	public List<Incident> getIncidentsByStatus(String status) {

		return incidentRepository.findByStatus(status);
	}
	public List<Incident> getHighPriorityHighSeverityIncidents() { 
		return incidentRepository.findByPriorityAndSeverity("high", "high");
	}

	public List<Incident> getOtherIncidents() { 
		return incidentRepository.findByPriorityNotAndSeverityNot("high"," high");
	} 

	public void updateIncidentResolution(Long incidentId, String resolution) throws NotFoundException {
		Optional<Incident> optionalIncident = incidentRepository.findById(incidentId);

		optionalIncident.ifPresent( incident-> {
			incident.setResolution(resolution);
			incident.setStatus("Resolved");
			incidentRepository.save(incident);
			emailService.sendResolutionMail(incidentId,incident.getUserEmailId(), resolution);
		});

		if (optionalIncident.isEmpty()) {
			throw new NotFoundException();
		}
	}

	   public Incident forwardIncident(Long incidentId, ForwardingRequest forwardingRequest) {
	        Incident originalIncident = findById(incidentId)
	                .orElseThrow(() -> new RuntimeException("Incident not found"));

	        originalIncident.setForwarded(true);
	        originalIncident.setStatus("Forwarded");
	        originalIncident.setForwardingmessage(forwardingRequest.getMessage());
	        originalIncident.setForwardedTo(forwardingRequest.getTargetGroup());
         return incidentRepository.save(originalIncident);
	    }

	public List<Incident> getIncidentsDueToday() {
		LocalDateTime startOfDay = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);
		LocalDateTime endOfDay = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX);
		System.out.println(startOfDay);
		System.out.println(endOfDay);
		return incidentRepository.findIncidentsDueToday(startOfDay, endOfDay);
	}

    public Incident assignIncidentToSelf(Long incidentId, String currentUser, String currentUserEmail) throws NotFoundException {
        Incident incident = incidentRepository.findById(incidentId)
                .orElseThrow(NotFoundException::new);

        if (incident.getAssignedTo() != null) {
            throw new ConflictException("Incident is already assigned.");
        }

        incident.setAssignedTeamEmail(currentUserEmail);
        incident.setAssignedTo(currentUser);
        incident.setStatus("Assigned");

        return incidentRepository.save(incident);
    } 
    public ResponseEntity<List<Incident>> searchIncidents(Long id, String userName) {
        try {
            if (id != null) {
                Incident incident = findById(id).orElseThrow(NotFoundException::new);
                return ResponseEntity.ok(Collections.singletonList(incident));
            } else if (userName != null) {
                List<Incident> incidents = getIncidentsByUser(userName);
                return ResponseEntity.ok(incidents);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }  
    }
    
} 



