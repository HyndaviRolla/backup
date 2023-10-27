package com.capstone.network.entities;


import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Component {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String componentName;

	@ManyToOne // Many components belong to one network element
	@JoinColumn(name = "network_element_id")
	private NetworkElement networkElement;
	
	public Component(Long id, String componentName, NetworkElement networkElement) {
		super();
		this.id = id;
		this.componentName = componentName;
		this.networkElement = networkElement;
	}
    
	public Component() {
	
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return Objects.equals(id, component.id) &&
                Objects.equals(componentName, component.componentName) &&
                Objects.equals(networkElement, component.networkElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, componentName, networkElement);
    }







}
