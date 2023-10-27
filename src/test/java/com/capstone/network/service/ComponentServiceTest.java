package com.capstone.network.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstone.network.entities.Component;
import com.capstone.network.entities.NetworkElement;
import com.capstone.network.repository.ComponentRepository;


public class ComponentServiceTest {

	@Mock
	private ComponentRepository componentRepository;

	@InjectMocks
	private ComponentService componentService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetComponentsByNetworkElement() {

		Long networkElementId = 1L;
		NetworkElement networkElement = new NetworkElement(networkElementId,"Element1");
		List<Component> mockComponents = Arrays.asList(
				new Component(1L, "Component1", networkElement),
				new Component(2L, "Component2", networkElement)
				);
		when(componentRepository.findByNetworkElementId(networkElementId)).thenReturn(mockComponents);

		List<Component> result = componentService.getComponentsByNetworkElement(networkElementId);

		assertEquals(2, result.size());

	}




}
