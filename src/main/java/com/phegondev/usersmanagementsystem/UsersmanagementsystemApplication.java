package com.phegondev.usersmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UsersmanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersmanagementsystemApplication.class, args);
	}

}
