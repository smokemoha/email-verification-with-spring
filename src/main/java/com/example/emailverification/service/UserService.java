package com.example.emailverification.service;

import com.example.emailverification.entity.Token;
import com.example.emailverification.entity.Role;
import com.example.emailverification.entity.User;
import com.example.emailverification.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service class responsible for user management operations including registration,
 * authentication, and email verification.
 * Implements UserDetailsService to integrate with Spring Security for authentication.
 */
@Service // Marks this class as a Spring service component for dependency injection
public class UserService implements UserDetailsService {

    // Dependencies required for user management operations
    private final UserRepository userRepository;       // For database operations related to users
    private final PasswordEncoder passwordEncoder;     // For securely encoding user passwords
    private final TokenService tokenService;           // For managing verification tokens
    private final EmailService emailService;           // For sending verification emails

    /**
     * Constructor-based dependency injection for required services
     * 
     * @param userRepository Repository for user data access
     * @param passwordEncoder Service for password encryption
     * @param tokenService Service for token management
     * @param emailService Service for email operations
     */
    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService,
            EmailService emailService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.emailService = emailService;
    }

    /**
     * Loads a user by username for Spring Security authentication
     * Implements UserDetailsService method
     * 
     * @param username The username to search for
     * @return UserDetails object containing user information
     * @throws UsernameNotFoundException if user not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    /**
     * Registers a new user in the system and initiates the email verification process
     * 
     * @param user The user object containing registration information
     * @return The saved user entity
     * @throws IllegalStateException if a user with the same username or email already exists
     */
    public User registerUser(User user) {
        // Check if user with username or email already exists
        userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
                .ifPresent(existingUser -> {
                    throw new IllegalStateException("User already exists");
                });

        // Encrypt the password before storing
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(Role.USER);  // Assign default user role

        // Save the user to the database
        userRepository.save(user);

        // Generate verification token
        String token = UUID.randomUUID().toString();
        Token confirmationToken = new Token(
                token,
                LocalDateTime.now(),              // Creation timestamp
                LocalDateTime.now().plusMinutes(15), // Expiration timestamp (15 minutes)
                user
        );

        // Save the token and send verification email
        tokenService.save(confirmationToken);
        emailService.sendSimpleMail(user.getEmail(), token);
        System.out.println(token);  // Debug output of token

        return user;
    }

    /**
     * Enables a user account after successful verification
     * 
     * @param user The user to enable
     */
    public void enableUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    /**
     * Confirms a verification token and enables the associated user account
     * 
     * @param token The token string to confirm
     * @throws IllegalArgumentException if token is invalid
     * @throws IllegalStateException if token is already confirmed or expired
     */
    @Transactional  // Ensures database operations are atomic
    public void confirmToken(String token) {
        // Find and validate the token
        Token confirmationToken = tokenService.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        // Check if token is already confirmed
        if(confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("User already confirmed");
        }

        // Check if token is expired
        LocalDateTime expiresAt = confirmationToken.getExpiresAt();
        if(expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        // Mark token as confirmed and enable the user
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        tokenService.save(confirmationToken);
        enableUser(confirmationToken.getUser());
    }
}