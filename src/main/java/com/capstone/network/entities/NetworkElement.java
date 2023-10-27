package com.capstone.network.entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class NetworkElement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String type;  
	private String ipAddress;
	private String manufacturer;
	private String model; 


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "network_element_id")
	private List<Component> components;


	public NetworkElement()
	{

	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	} 

	public List<Component> getComponents() {
		return components;
	}


	public void setComponents(List<Component> components) {
		this.components = components;
	}


	public NetworkElement(Long id, String name, String type, String ipAddress, String manufacturer, String model) { 
		this.id = id;
		this.name = name;
		this.type = type;
		this.ipAddress = ipAddress;
		this.manufacturer = manufacturer;
		this.model = model;

	}

	public NetworkElement(long l, String name) {
		 this.id=l;
		 this.name=name;
	}


}
