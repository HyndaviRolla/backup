package com.capstone.network.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.network.entities.Component;

import java.util.List;

public interface ComponentRepository extends JpaRepository<Component, Long> {
 
    List<Component> findByNetworkElementId(Long networkElementId);
}
