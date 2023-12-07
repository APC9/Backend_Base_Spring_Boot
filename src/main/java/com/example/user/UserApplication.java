package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		// Deshabilitando configuración de seguridad por defecto proporcionada por Spring Boot
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class 
	}
)
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
