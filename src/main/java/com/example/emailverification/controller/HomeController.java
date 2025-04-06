package com.example.emailverification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class responsible for handling home page related HTTP requests.
 * Maps URLs related to the home page to specific handler methods.
 */
@Controller // Marks this class as a Spring MVC controller
public class HomeController {

    /**
     * Handles GET requests to the /home endpoint
     * Renders the home view template when users navigate to the home page
     * This page is typically shown after successful authentication
     * 
     * @return The name of the view template to render ("home.html")
     */
    @GetMapping("/home") // Maps HTTP GET requests to /home to this method
    public String home() {
        return "home"; // Returns the view name (Thymeleaf will look for home.html)
    }
}