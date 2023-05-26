package com.project.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BinApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinApplication.class, args);
	}

}
