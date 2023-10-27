package com.capstone.network;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
@EnableWebSecurity
@SpringBootApplication
public class NetworkApplication {
	public static void main(String[] args) {
		SpringApplication.run(NetworkApplication.class, args);
	}
} 


