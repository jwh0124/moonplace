package edu.circle.moonplace.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MoonplaceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoonplaceApiApplication.class, args);
	}

}
