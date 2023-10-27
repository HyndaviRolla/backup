package com.capstone.network.service;
 import org.springframework.stereotype.Service;

import com.capstone.network.entities.Component;
import com.capstone.network.repository.ComponentRepository;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    
    public List<Component> getComponentsByNetworkElement(Long networkElementId) {
 
        return componentRepository.findByNetworkElementId(networkElementId);
    }
}
