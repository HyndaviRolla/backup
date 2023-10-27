package com.capstone.network.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.network.entities.NetworkElement;

public interface NetworkElementRepository extends JpaRepository<NetworkElement, Long> {

	Optional<NetworkElement> findByName(String name);

	List<NetworkElement> findByNameContainingIgnoreCase(String search);
}
