package com.capstone.network.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.network.entities.Component;
import com.capstone.network.entities.NetworkElement;
import com.capstone.network.repository.NetworkElementRepository;

@RestController
@RequestMapping("/api/network-elements")
@CrossOrigin
public class NetworkElementController {
	@Autowired
	private NetworkElementRepository networkElementRepository;

	@GetMapping
	public List<NetworkElement> getAllNetworkElements() {
		return networkElementRepository.findAll();
	}

	@PostMapping
	public NetworkElement addNetworkElement(@RequestBody NetworkElement networkElement) {
		return networkElementRepository.save(networkElement);
	}

	@PostMapping("/{id}/add-component")
	public NetworkElement addComponentToNetworkElement(
			@PathVariable Long id,
			@RequestBody Component component) {
		NetworkElement networkElement = networkElementRepository.findById(id).orElseThrow(() -> new RuntimeException("Network Element not found with id: " + id));

		networkElement.getComponents().add(component);
		return networkElementRepository.save(networkElement);
	}
	@GetMapping("/{name}")
	public NetworkElement getNetworkElementByName(@PathVariable String name) {
		return networkElementRepository.findByName(name).orElseThrow(() -> new RuntimeException("Network Element not found with name: " + name));
	}
	
}
