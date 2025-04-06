package com.example.emailverification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class responsible for handling login-related HTTP requests.
 * Maps URLs related to the login process to specific handler methods.
 */
@Controller // Marks this class as a Spring MVC controller
public class LoginController {

    /**
     * Handles GET requests to the /login endpoint
     * Renders the login view template when users navigate to the login page
     * 
     * @return The name of the view template to render ("login.html")
     */
    @GetMapping("/login") // Maps HTTP GET requests to /login to this method
    public String login() {
        return "login"; // Returns the view name (Thymeleaf will look for login.html)
    }
}