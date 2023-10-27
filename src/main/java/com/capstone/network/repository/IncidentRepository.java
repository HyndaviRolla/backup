package com.capstone.network.repository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capstone.network.entities.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
	List<Incident> findByForwardedToAndForwardedIsTrue(String group);
	List<Incident> findByAssignmentGroup(String assignmentGroup);  
	List<Incident> findByPriorityAndSeverity(String severity, String priority);
	List<Incident> findByPriorityNotAndSeverityNot(String severity,String priority);
	//	List<Incident> findByUserName(String userName);
	List<Incident> findByUser(String userName);

	@Query("SELECT i FROM Incident i WHERE i.slaEndTime >= :startOfDay AND i.slaEndTime < :endOfDay")
	List<Incident> findIncidentsDueToday(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

	List<Incident> findByStatus(String status);




}
