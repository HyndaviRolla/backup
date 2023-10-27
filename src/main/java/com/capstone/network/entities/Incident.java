package com.capstone.network.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Incident {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "network_element_id")
	private NetworkElement networkElement;

	@ManyToOne
	@JoinColumn(name = "component_id")  
	private Component component;

	private String user;
	
	private String assignedTo;
	
	private String assignedTeamEmail;

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getAssignedTeamEmail() {
		return assignedTeamEmail;
	}

	public void setAssignedTeamEmail(String assignedTeamEmail) {
		this.assignedTeamEmail = assignedTeamEmail;
	}

	private String description; 

	private String issueType;

	private String severity;

	private String priority;

	private String createdTime; 

	private String resolution;

	private String userEmailId;
 
	private LocalDateTime slaStartTime;
	
	private LocalDateTime slaEndTime;
	
	private boolean slaBreached;
 
	private String assignmentGroup;

	private String forwardingmessage;

	private String forwardedTo;

	private boolean forwarded;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getForwardedTo() {
		return forwardedTo;
	}

	public void setForwardedTo(String forwardedTo) {
		this.forwardedTo = forwardedTo;
	}

	public String getForwardingmessage() {
		return forwardingmessage;
	}

	public void setForwardingmessage(String forwardingmessage) {
		this.forwardingmessage = forwardingmessage;
	}

	public boolean isForwarded() {
		return forwarded;
	}

	public void setForwarded(boolean forwarded) {
		this.forwarded = forwarded;
	}

	public String getAssignmentGroup() {
		return assignmentGroup;
	}

	public void setAssignmentGroup(String assignmentGroup) {
		this.assignmentGroup = assignmentGroup;
	}

	public String getUserEmailId() {
		return userEmailId;
	}
    
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NetworkElement getNetworkElement() {
		return networkElement;
	}

	public void setNetworkElement(NetworkElement networkElement) {
		this.networkElement = networkElement;
	}

	public Component getComponent() {
		return component;
	}


	public void setComponent(Component component) {
		this.component = component;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public boolean isSlaBreached() {
		return slaBreached;
	}

	public void setSlaBreached(boolean slaBreached) {
		this.slaBreached = slaBreached;
	}

	public LocalDateTime getSlaStartTime() {
		return slaStartTime;
	}

	public void setSlaStartTime(LocalDateTime slaStartTime) {
		this.slaStartTime = slaStartTime;
	}

	public LocalDateTime getSlaEndTime() {
		return slaEndTime;
	}

	public void setSlaEndTime(LocalDateTime slaEndTime) {
		this.slaEndTime = slaEndTime;
	}
}

