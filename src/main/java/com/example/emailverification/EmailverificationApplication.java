package com.example.emailverification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EmailverificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailverificationApplication.class, args);
	}

}
