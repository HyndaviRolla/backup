package com.capstone.network.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstone.network.entities.NetworkElement;
import com.capstone.network.repository.NetworkElementRepository;

public class NetworkElementServiceTest {

    @Mock
    private NetworkElementRepository networkElementRepository;

    @InjectMocks
    private NetworkElementService networkElementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllNetworkElements() {
        // Mock the behavior of the repository
        List<NetworkElement> mockNetworkElements = Arrays.asList(
                new NetworkElement(1L, "Element1"),
                new NetworkElement(2L, "Element2")
        );
        when(networkElementRepository.findAll()).thenReturn(mockNetworkElements);
 
        List<NetworkElement> result = networkElementService.getAllNetworkElements();
 
        assertEquals(2, result.size()); 
    }

    @Test
    public void testGetNetworkElementById() { 
        Long elementId = 1L;
        NetworkElement mockNetworkElement = new NetworkElement(elementId, "Element1");
        when(networkElementRepository.findById(elementId)).thenReturn(Optional.of(mockNetworkElement));
 
        NetworkElement result = networkElementService.getNetworkElementById(elementId);
 
        assertEquals("Element1", result.getName()); 
    }

    @Test
    public void testSearchNetworkElements() { 
        String searchString = "Element";
        List<NetworkElement> mockNetworkElements = Arrays.asList(
                new NetworkElement(1L, "Element1"),
                new NetworkElement(2L, "Element2")
        );
        when(networkElementRepository.findByNameContainingIgnoreCase(searchString)).thenReturn(mockNetworkElements);
 
        List<NetworkElement> result = networkElementService.searchNetworkElements(searchString);
 
        assertEquals(2, result.size()); 
    }
}
