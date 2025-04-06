package com.example.emailverification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Configuration class for password encoding.
 * Provides a bean for password encryption used during user registration and authentication.
 */
@Component // Marks this class as a Spring component for dependency injection
public class PasswordConfig {

    /**
     * Creates and configures a password encoder bean.
     * BCryptPasswordEncoder is a secure implementation that uses the BCrypt strong hashing function.
     * 
     * @return A configured PasswordEncoder instance for securely hashing passwords
     */
    @Bean // Marks this method as a bean provider for Spring container
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Uses BCrypt algorithm with default strength (10)
    }
}