package com.example.radioadsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RadioadsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(RadioadsappApplication.class, args);
	}

}
