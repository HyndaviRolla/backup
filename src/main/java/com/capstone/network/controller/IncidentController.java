package com.capstone.network.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capstone.network.DTO.ForwardingRequest;
import com.capstone.network.entities.Component;
import com.capstone.network.entities.Incident;
import com.capstone.network.entities.NetworkElement;
import com.capstone.network.exception.BadRequestException;
import com.capstone.network.exception.ConflictException;
import com.capstone.network.exception.InternalServerErrorException;
import com.capstone.network.service.ComponentService;
import com.capstone.network.service.IncidentService;
import com.capstone.network.service.NetworkElementService;
import com.capstone.network.service.UserService; 
@RestController
@RequestMapping("/api/incidents")
@CrossOrigin  
public class IncidentController {

	@Autowired IncidentService incidentService;

	@Autowired ComponentService componentService;

	@Autowired UserService userService;


	@Autowired NetworkElementService networkElementService;

	@PostMapping
	public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
		try {
			if (incident == null || incident.getNetworkElement() == null || incident.getComponent() == null) {
				throw new BadRequestException("Invalid request body or missing required parameters.");
			}
			Incident savedIncident = incidentService.saveIncident(incident);
			return ResponseEntity.ok(savedIncident);
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to create incident.");
		}
	}


	@GetMapping("/network-elements")
	public ResponseEntity<List<NetworkElement>> getNetworkElements(@RequestParam String search) {
		try {
			List<NetworkElement> networkElements = networkElementService.searchNetworkElements(search);
			return ResponseEntity.ok(networkElements);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to retrieve network elements.");
		}
	}

	@GetMapping("/components/{networkElementId}")
	public ResponseEntity<List<Component>> getComponents(@PathVariable Long networkElementId) {
		try {
			List<Component> components = componentService.getComponentsByNetworkElement(networkElementId);
			return ResponseEntity.ok(components);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to retrieve components.");
		}
	} 

	@PostMapping("/forward/{incidentId}")
	public ResponseEntity<Incident> forwardIncident(@PathVariable Long incidentId,
			@RequestBody ForwardingRequest forwardingRequest) {
		try {
			Incident updatedIncident = incidentService.forwardIncident(incidentId, forwardingRequest);
			return ResponseEntity.ok(updatedIncident);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to forward incident.");
		}
	}

	

	@GetMapping("/assigned-group/{assignmentGroup}")
	public ResponseEntity<List<Incident>> getIncidentsByAssignmentGroup(@PathVariable String assignmentGroup) {
		try {
			List<Incident> incidents = incidentService.getIncidentsByAssignmentGroup(assignmentGroup);
			return ResponseEntity.ok(incidents);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to retrieve incidents by assignment group.");
		}
	} 

	@GetMapping("/user/{userName}")
	public ResponseEntity<List<Incident>> getIncidentsByUserName(@PathVariable String userName) {
		try {
			List<Incident> incidents = incidentService.getIncidentsByUser(userName);
			return ResponseEntity.ok(incidents);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to retrieve incidents by user name.");
		}
	}

	@GetMapping("/search")
	public ResponseEntity<List<Incident>> searchIncidents(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String userName) {
		try {
			return incidentService.searchIncidents(id, userName);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to search incidents.");
		}
	}

	@GetMapping("/forwarded/{group}")
	public ResponseEntity<List<Incident>> getForwardedIncidents(@PathVariable String group) {
		try {
			List<Incident> forwardedIncidents = incidentService.getForwardedIncidents(group);
			return ResponseEntity.ok(forwardedIncidents);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to retrieve forwarded incidents.");
		}
	}

	@GetMapping("/high-priority-high-severity")
	public ResponseEntity<List<Incident>> getHighPriorityHighSeverityIncidents() {
		try {
			List<Incident> highPriorityHighSeverityIncidents = incidentService.getHighPriorityHighSeverityIncidents();
			return ResponseEntity.ok(highPriorityHighSeverityIncidents);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to retrieve high priority high severity incidents.");
		}
	}

	@GetMapping("/other-incidents")
	public ResponseEntity<List<Incident>> getOtherIncidents() {
		try {
			List<Incident> otherIncidents = incidentService.getOtherIncidents();
			return ResponseEntity.ok(otherIncidents);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to retrieve other incidents.");
		}
	}

	@PutMapping("/incidents/{id}/resolution")
	public ResponseEntity<Void> updateIncidentResolution(@PathVariable Long id, @RequestBody ResolutionRequest resolutionRequest) {
		try {
			incidentService.updateIncidentResolution(id, resolutionRequest.getResolution());
			return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to update incident resolution.");
		}
	}

	@GetMapping("/due-today")
	public ResponseEntity<List<Incident>> getIncidentsDueToday() {
		try {
			List<Incident> dueTodayIncidents = incidentService.getIncidentsDueToday();
			return ResponseEntity.ok(dueTodayIncidents);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to retrieve incidents due today.");
		}
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<Incident>> getIncidentsByStatus(@PathVariable String status) {
		try {
			List<Incident> incidents = incidentService.getIncidentsByStatus(status);
			return ResponseEntity.ok(incidents);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to retrieve incidents by status.");
		}
	}

	@PostMapping("/{incidentId}/assign-to-self")
	public ResponseEntity<Incident> assignIncidentToSelf(
			@PathVariable Long incidentId,
			@RequestParam String currentUser,
			@RequestParam String currentUserEmail) {
		try {
			Incident updatedIncident = incidentService.assignIncidentToSelf(incidentId, currentUser, currentUserEmail);
			return ResponseEntity.ok(updatedIncident);
		} catch (Exception e) {
			throw new InternalServerErrorException("Failed to assign incident to self.");
		}
	}


}

