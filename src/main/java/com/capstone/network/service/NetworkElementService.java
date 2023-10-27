package com.capstone.network.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.network.entities.NetworkElement;
import com.capstone.network.repository.NetworkElementRepository;

@Service
public class NetworkElementService {
 @Autowired
 private NetworkElementRepository networkElementRepository;

 public List<NetworkElement> getAllNetworkElements() {
     return networkElementRepository.findAll();
 }

 
 public NetworkElement getNetworkElementById(Long id) {
     return networkElementRepository.findById(id).orElse(null);
 }
 
 public List<NetworkElement> searchNetworkElements(String search) {
    
     return networkElementRepository.findByNameContainingIgnoreCase(search);
 }
 
}





