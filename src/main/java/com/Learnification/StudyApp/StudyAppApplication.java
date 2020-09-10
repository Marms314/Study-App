package com.Learnification.StudyApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class StudyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyAppApplication.class, args);
	}

}
