package com.example.emailverification.controller;

import com.example.emailverification.entity.User;
import com.example.emailverification.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller class responsible for handling user registration and email verification.
 * Maps URLs related to the registration process to specific handler methods.
 */
@Controller // Marks this class as a Spring MVC controller
@RequestMapping("register") // Base URL path for all methods in this controller
public class AuthenticationController {

    // Service dependency for user-related operations
    private final UserService userService;

    /**
     * Constructor-based dependency injection for user service
     * 
     * @param userService Service for user registration and verification
     */
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles GET requests to the /register endpoint
     * Displays the registration form to the user
     * 
     * @param model Spring MVC model for passing data to the view
     * @return The name of the view template to render ("register.html")
     */
    @GetMapping // Maps HTTP GET requests to /register to this method
    public String register(Model model) {
        User user = new User(); // Create empty user object for form binding
        model.addAttribute("user", user); // Add user object to model for form binding
        return "register"; // Returns the view name (Thymeleaf will look for register.html)
    }

    /**
     * Handles POST requests to the /register endpoint
     * Processes the registration form submission
     * 
     * @param user User object populated from form data via @ModelAttribute
     * @param model Spring MVC model for passing data to the view
     * @param redirectAttributes For passing flash attributes across redirects
     * @return Redirect to registration page with success message
     */
    @PostMapping // Maps HTTP POST requests to /register to this method
    public String register(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
        userService.registerUser(user); // Register the user and send verification email
        // Add flash attribute for success message (persists across redirect)
        redirectAttributes.addFlashAttribute("success", "Please confirm your email address");
        return "redirect:/register"; // Redirect to avoid form resubmission
    }

    /**
     * Handles GET requests to the /register/confirmToken endpoint
     * Processes email verification token confirmation
     * 
     * @param token The verification token from the email link
     * @param model Spring MVC model for passing data to the view
     * @return The name of the view template to render ("confirmToken.html")
     */
    @GetMapping("/confirmToken") // Maps HTTP GET requests to /register/confirmToken to this method
    public String confirmToken(@RequestParam("token") String token, Model model) {
        userService.confirmToken(token); // Validate token and enable user account
        return "confirmToken"; // Returns the view name (Thymeleaf will look for confirmToken.html)
    }
}