package com.example.emailverification.config;

import com.example.emailverification.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security settings.
 * Defines security rules, authentication methods, and access control for the application.
 */
@Configuration // Marks this class as a Spring configuration class
@EnableWebSecurity // Enables Spring Security web security support
public class SecurityConfig {

    // User service dependency for authentication
    private final UserService userService;

    /**
     * Constructor-based dependency injection for user service
     * 
     * @param userService Service that implements UserDetailsService for authentication
     */
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    /**
     * Configures the security filter chain with authorization rules,
     * login settings, and logout behavior
     * 
     * @param http HttpSecurity object to configure
     * @return Configured SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configure authorization rules for HTTP requests
                .authorizeHttpRequests(
                        req->req
                                // Public resources that don't require authentication
                                .requestMatchers("/register/**", "/login", "/css/**", "/js/**", "/confirm/**").permitAll()
                                // All other requests require authentication
                                .anyRequest().authenticated()
                )
                // Configure form-based login
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page URL
                        .loginProcessingUrl("/login") // URL to submit the login form
                        .defaultSuccessUrl("/home", true) // Redirect after successful login
                        .permitAll() // Allow anyone to access the login page
                )
                // Configure logout behavior
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // Redirect after logout
                        .permitAll() // Allow anyone to logout
                )
                // Configure the user details service for authentication
                .userDetailsService(userService);
        
        return http.build();
    }
}