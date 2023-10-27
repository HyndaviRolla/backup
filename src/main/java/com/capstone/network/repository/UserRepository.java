package com.capstone.network.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capstone.network.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {


	Optional<User> findByName(String username);
 

	User findUserByname(String loggedInUsername);
 

	  @Query("SELECT u.role FROM User u WHERE u.name = :username")
	    String getRoleByName(@Param("username") String username);

	  @Query("SELECT u.email FROM User u WHERE u.name = :username")
	String getEmailByName(@Param("username")String username);
 


}