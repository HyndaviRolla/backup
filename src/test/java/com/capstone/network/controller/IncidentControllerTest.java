// package com.capstone.network.controller;
// import com.capstone.network.entities.Incident;
//import com.capstone.network.entities.NetworkElement;
//import com.capstone.network.DTO.ForwardingRequest;
//import com.capstone.network.entities.Component;
//import com.capstone.network.service.IncidentService;
//import com.capstone.network.service.NetworkElementService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class IncidentControllerTest {
//
//    @Mock
//    private IncidentService incidentService;
//    @Mock
//    private NetworkElementService networkElementService;
//
//    @InjectMocks
//    private IncidentController incidentController;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    public void testCreateIncident() {
//        // Mocking
//        Incident inputIncident = createSampleIncident(); // Creating a sample incident
//        when(incidentService.saveIncident(any())).thenReturn(inputIncident);
//
//        // Execution
//        ResponseEntity<Incident> responseEntity = incidentController.createIncident(inputIncident);
//
//        // Assertion
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(inputIncident, responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateIncidentBadRequest() {
//        // Execution
//        ResponseEntity<Incident> responseEntity = incidentController.createIncident(null);
//
//        // Assertion
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//    }
//
//    // Add more test methods for other controller methods...
//
//    private Incident createSampleIncident() {
//        Incident sampleIncident = new Incident();
//        sampleIncident.setId(1L);
//        sampleIncident.setIssueType("hardware");
//
//        // Create and set a sample NetworkElement
//        NetworkElement networkElement = new NetworkElement();
//        networkElement.setId(100L);
//        sampleIncident.setNetworkElement(networkElement);
//
//        // Create and set a sample Component
//        Component component = new Component();
//        component.setId(200L);
//        sampleIncident.setComponent(component);
//
//        // Set other properties as needed, e.g., sampleIncident.setPriority("High");
//
//        return sampleIncident;
//    }
//
//    @Test
//    public void testGetNetworkElements() {
//     
//		// Mocking
//        when(networkElementService.searchNetworkElements(anyString())).thenReturn(Collections.emptyList());
//
//        // Execution
//        ResponseEntity<List<NetworkElement>> responseEntity = incidentController.getNetworkElements("search");
//
//        // Assertion
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(Collections.emptyList(), responseEntity.getBody());
//    }
//
//    @Test
//    public void testGetNetworkElementsInternalServerError() {
//        // Mocking
//        when(networkElementService.searchNetworkElements(anyString())).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<List<NetworkElement>> responseEntity = incidentController.getNetworkElements("search");
//
//        // Assertion
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//
//    // Add more test methods for other controller methods...
//
//    @Test
//    public void testForwardIncident() {
//        // Mocking
//        Long incidentId = 1L;
//        ForwardingRequest forwardingRequest = new ForwardingRequest();
//        Incident originalIncident = createSampleIncident(); // Creating a sample incident
//        when(incidentService.findById(anyLong())).thenReturn(java.util.Optional.of(originalIncident));
//        when(incidentService.saveIncident(any())).thenReturn(originalIncident);
//
//        // Execution
//        ResponseEntity<Incident> responseEntity = incidentController.forwardIncident(incidentId, forwardingRequest);
//
//        // Assertion
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(originalIncident, responseEntity.getBody());
//    }
//
//    @Test
//    public void testForwardIncidentIncidentNotFound() {
//        // Mocking
//        Long incidentId = 1L;
//        ForwardingRequest forwardingRequest = new ForwardingRequest();
//        when(incidentService.findById(anyLong())).thenReturn(java.util.Optional.empty());
//
//        // Execution
//        ResponseEntity<Incident> responseEntity = incidentController.forwardIncident(incidentId, forwardingRequest);
//
//        // Assertion
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
//
//    // Add more test methods for other controller methods...
//}
