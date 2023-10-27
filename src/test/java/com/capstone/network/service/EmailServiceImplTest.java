//package com.capstone.network.service;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import java.io.IOException;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import com.capstone.network.entities.Incident;
//import com.capstone.network.repository.IncidentRepository;
//
//public class EmailServiceImplTest {
//
//    @Mock
//    private JavaMailSender javaMailSender;
//
//    @Mock
//    private IncidentRepository incidentRepository;
//
//    @InjectMocks
//    private EmailServiceImpl emailService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testSendResolutionMail_Successful() {
//        Long incidentId = 1L;
//        String userEmail = "user@example.com";
//        String resolution = "Resolution details";
//
//        Incident mockIncident = createMockIncident(incidentId, "Test User", "Incident Description", resolution);
//
//        when(incidentRepository.findById(incidentId)).thenReturn(java.util.Optional.of(mockIncident));
//
//        String result = emailService.sendResolutionMail(incidentId, userEmail, resolution);
//
//        assertEquals("Mail Sent Successfully...", result);
//
//        verifyEmailSending(userEmail, "Incident Resolution", buildResolutionEmailBody(mockIncident));
//    }
//
//    @Test
//    public void testSendResolutionMail_IncidentNotFound() {
//        // Similar structure as the testSendResolutionMail_Successful
//        // Make sure to verify that JavaMailSender's send method was not called
//    }
//
//    @Test
//    public void testSendAcknoweledgment_Successful() {
//        Long incidentId = 3L;
//        String userEmail = "user@example.com";
//
//        Incident mockIncident = createMockIncident(incidentId, "Test User", "Incident Description", null);
//
//        when(incidentRepository.findById(incidentId)).thenReturn(java.util.Optional.of(mockIncident));
//
//        String result = emailService.sendAcknoweledgment(incidentId, userEmail);
//
//        assertEquals("Mail Sent Successfully...", result);
//
//        verifyEmailSending(userEmail, "Acknowledgment Mail", buildAckResolutionEmailBody(mockIncident));
//    }
//
//    @Test
//    public void testSendSLAMail_Successful() {
//        Long incidentId = 4L;
//        String userEmail = "user@example.com";
//
//        Incident mockIncident = createMockIncident(incidentId, "Test User", "Incident Description", null);
//
//        when(incidentRepository.findById(incidentId)).thenReturn(java.util.Optional.of(mockIncident));
//
//        String result = emailService.sendSLAMail(incidentId, userEmail);
//
//        assertEquals("Mail Sent Successfully...", result);
//
//        verifyEmailSending(userEmail, "DEadline Mail", buildSlaEmailBody(mockIncident));
//    }
//
//    private Incident createMockIncident(Long id, String user, String description, String resolution) {
//        Incident incident = new Incident();
//        incident.setId(id);
//        incident.setUser(user);
//        incident.setDescription(description);
//        incident.setResolution(resolution);
//        return incident;
//    }
//
//    private void verifyEmailSending(String to, String subject, String expectedBody) throws IOException {
//        ArgumentCaptor<MimeMessage> mimeMessageCaptor = ArgumentCaptor.forClass(MimeMessage.class);
//        verify(javaMailSender).send(mimeMessageCaptor.capture());
//
//        MimeMessage sentMimeMessage = mimeMessageCaptor.getValue();
//
//        try {
//            assertEquals(to, sentMimeMessage.getRecipients(MimeMessage.RecipientType.TO)[0].toString());
//            assertEquals(subject, sentMimeMessage.getSubject());
//            assertEquals(expectedBody, sentMimeMessage.getContent());
//        } catch (MessagingException e) {
//            throw new RuntimeException("Error while verifying email sending", e);
//        }
//    }
//}
//
//
