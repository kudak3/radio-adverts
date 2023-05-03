package com.example.radioadsapp;

import com.example.radioadsapp.service.impl.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class RadioadsappApplication {
	@Bean
	AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
	public static void main(String[] args) {
		SpringApplication.run(RadioadsappApplication.class, args);
	}

}
