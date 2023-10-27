package com.capstone.network.controller;
import com.capstone.network.entities.Component;
import com.capstone.network.entities.NetworkElement;
import com.capstone.network.repository.NetworkElementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NetworkElementControllerTest {

    @Mock
    private NetworkElementRepository networkElementRepository;

    @InjectMocks
    private NetworkElementController networkElementController;

    @Test
    void getAllNetworkElements() {
        // Arrange
        List<NetworkElement> mockElements = new ArrayList<>();
        when(networkElementRepository.findAll()).thenReturn(mockElements);

        // Act
        List<NetworkElement> result = networkElementController.getAllNetworkElements();

        // Assert
        assertEquals(mockElements, result);
        verify(networkElementRepository, times(1)).findAll();
    }

    @Test
    void addNetworkElement() {
        // Arrange
        NetworkElement mockElement = new NetworkElement();
        when(networkElementRepository.save(any())).thenReturn(mockElement);

        // Act
        NetworkElement result = networkElementController.addNetworkElement(new NetworkElement());

        // Assert
        assertEquals(mockElement, result);
        verify(networkElementRepository, times(1)).save(any());
    }
    
    @Test
    void addComponentToNetworkElement() {
        // Arrange
        Long id = 1L;
        NetworkElement existingNetworkElement = new NetworkElement();
        existingNetworkElement.setId(id);  // Assuming your NetworkElement has an id property
        existingNetworkElement.setComponents(new ArrayList<>());  // Assuming your NetworkElement has a setComponents method

        when(networkElementRepository.findById(id)).thenReturn(Optional.of(existingNetworkElement));
        when(networkElementRepository.save(any())).thenAnswer(invocation -> {
            NetworkElement savedElement = invocation.getArgument(0);
            savedElement.setId(id);  // Assuming your NetworkElement has an id property
            return savedElement;
        });

        // Act
        NetworkElement result = networkElementController.addComponentToNetworkElement(id, new Component());

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getComponents().size());
        assertEquals(id, result.getId());  // Assuming your NetworkElement has an id property

        // Verify that the findById and save methods are called with the expected arguments
        verify(networkElementRepository, times(1)).findById(id);
        verify(networkElementRepository, times(1)).save(any());
    } 
    @Test
    void getNetworkElementByName() {
        // Arrange
        String name = "TestElement";
        NetworkElement mockElement = new NetworkElement();
        when(networkElementRepository.findByName(name)).thenReturn(Optional.of(mockElement));

        // Act
        NetworkElement result = networkElementController.getNetworkElementByName(name);

        // Assert
        assertEquals(mockElement, result);
        verify(networkElementRepository, times(1)).findByName(name);
    }
}
