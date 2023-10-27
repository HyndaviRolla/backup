package com.capstone.network.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service; 
import com.capstone.network.entities.Incident;  
import com.capstone.network.repository.IncidentRepository; 


@Service
public class EmailServiceImpl   {
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private IncidentRepository incidentRepository;
	public String sendResolutionMail(Long incidentId, String userEmail, String resolution) {
		try {

			java.util.Optional<Incident> optionalIncident = incidentRepository.findById(incidentId);
			if (optionalIncident.isPresent()) {
				Incident incident = optionalIncident.get();
				String subject = "Incident Resolution";
				String body = buildResolutionEmailBody(incident);
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom(sender);
				helper.setTo(userEmail);
				helper.setSubject(subject);
				helper.setText(body, true);
				javaMailSender.send(mimeMessage);
				return "Mail Sent Successfully...";
			} else {
				return "Incident not found for ID: " + incidentId;
			}
		} catch (Exception e) {
			return "Error while Sending Mail: " + e.getMessage();
		}
	}

	public String sendAcknoweledgment(Long incidentId, String userEmail) {
		try {

			java.util.Optional<Incident> optionalIncident = incidentRepository.findById(incidentId);
			if (optionalIncident.isPresent()) {
				Incident incident = optionalIncident.get();
				String subject ="Acknowledgment Mail";
				String body = buildAckResolutionEmailBody(incident);
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom(sender); 
				helper.setTo(userEmail);
				helper.setSubject(subject);
				helper.setText(body, true);
				javaMailSender.send(mimeMessage);
				return "Mail Sent Successfully...";
			} else {
				return "Incident not found for ID: " + incidentId;
			}
		} catch (Exception e) {
			return "Error while Sending Mail: " + e.getMessage();
		}
	} 

	public String sendSLAMail(Long incidentId, String userEmail) {
		try {

			java.util.Optional<Incident> optionalIncident = incidentRepository.findById(incidentId);
			if (optionalIncident.isPresent()) {
				Incident incident = optionalIncident.get();
				String subject ="DEadline Mail";
				String body = buildSlaEmailBody(incident);
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom(sender); 
				helper.setTo(userEmail);
				helper.setSubject(subject);
				helper.setText(body, true);
				javaMailSender.send(mimeMessage);
				return "Mail Sent Successfully...";
			} else {
				return "Incident not found for ID: " + incidentId;
			}
		} catch (Exception e) {
			return "Error while Sending Mail: " + e.getMessage();
		}
	}
	private String buildSlaEmailBody(Incident incident) {
		return "Dear " + incident.getUser() + ",<br><br>" +
				"I hope this email finds you well. We wanted to inform you that the incident with ID"+incident.getId() +"is not yet resolved.Please look into it"+

				"Incident Details:<br>" +
				"- Description: " + incident.getDescription() + "<br>" + 
				"Thank you for your understanding.<br><br>" +
				"Best regards,<br>" +
				"[Support Team]";
	}

	private String buildResolutionEmailBody(Incident incident) {
		return "Dear " + incident.getUser() + ",<br><br>" +
				"We are writing to inform you that the incident with ID " + incident.getId() + " has been resolved.<br><br>" +
				"Incident Details:<br>" +
				"- Description: " + incident.getDescription() + "<br>" +
				"- Resolution: " + incident.getResolution() + "<br><br>" +
				"Thank you for your understanding.<br><br>" +
				"Best regards,<br>" +
				"[Support Team]";
	}
	private String buildAckResolutionEmailBody(Incident incident) {
		return "Dear " + incident.getUser() + ",<br><br>" +
				"I hope this email finds you well. We wanted to inform you that we have received your support incident with ID"+incident.getId() +"and acknowledge the issue you've reported. Our team is committed to providing you with the best possible assistance, and we are currently reviewing the details you provided"+

				"Incident Details:<br>" +
				"- Description: " + incident.getDescription() + "<br>" + 
				"Thank you for your understanding.<br><br>" +
				"Best regards,<br>" +
				"[Support Team]";
	}


}




