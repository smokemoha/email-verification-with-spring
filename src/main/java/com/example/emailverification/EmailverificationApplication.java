package com.example.emailverification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main application class for the Email Verification System.
 * This class serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication  // Enables auto-configuration, component scanning, and defines this as the main configuration class
@EnableAsync  // Enables asynchronous method execution, allowing methods to run in separate threads (used for email sending)
public class EmailverificationApplication {

    /**
     * Main method that starts the Spring Boot application.
     * This method bootstraps the application by calling SpringApplication.run()
     * 
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(EmailverificationApplication.class, args);
    }

}