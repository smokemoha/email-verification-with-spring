package com.example.emailverification.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Entity class representing an email verification token.
 * This class stores tokens sent to users for email verification.
 */
@Entity // Marks this class as a JPA entity
@Table(name = "confirmatin_token") // Specifies the table name in the database
public class Token {

    /**
     * Primary key for the token entity
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Uses a database sequence for ID generation
    private Long id;

    /**
     * The actual token string sent to the user's email
     */
    @Column(nullable = false) // Token is required
    private String token;

    /**
     * Timestamp when the token was created
     */
    @Column(nullable = false) // Creation timestamp is required
    private LocalDateTime createdAt;

    /**
     * Timestamp when the token expires
     * Tokens are typically valid for a limited time (e.g., 15 minutes)
     */
    @Column(nullable = false) // Expiration timestamp is required
    private LocalDateTime expiresAt;

    /**
     * Timestamp when the token was confirmed by the user
     * Null if the token has not been confirmed yet
     */
    private LocalDateTime confirmedAt;

    /**
     * The user associated with this token
     * Many tokens can belong to one user (Many-to-One relationship)
     */
    @ManyToOne // Defines a many-to-one relationship with User entity
    @JoinColumn(
            nullable = false, // User association is required
            name = "app_user_id" // Name of the foreign key column in the database
    )
    private User user;

    /**
     * Default constructor required by JPA
     */
    public Token() {
    }

    /**
     * Parameterized constructor for creating a new token
     * 
     * @param token The token string
     * @param createdAt When the token was created
     * @param expiresAt When the token expires
     * @param user The user associated with this token
     */
    public Token(
            String token,
            LocalDateTime createdAt,
            LocalDateTime expiresAt,
            User user
    ) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    /**
     * Gets the token's ID
     * 
     * @return The token's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the token's ID
     * 
     * @param id The ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the token string
     * 
     * @return The token string
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token string
     * 
     * @param token The token string to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the token creation timestamp
     * 
     * @return The creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the token creation timestamp
     * 
     * @param createdAt The creation timestamp to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the token expiration timestamp
     * 
     * @return The expiration timestamp
     */
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    /**
     * Sets the token expiration timestamp
     * 
     * @param expiresAt The expiration timestamp to set
     */
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * Gets the token confirmation timestamp
     * 
     * @return The confirmation timestamp, or null if not confirmed
     */
    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    /**
     * Sets the token confirmation timestamp
     * Used when a user confirms their email
     * 
     * @param confirmedAt The confirmation timestamp to set
     */
    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    /**
     * Gets the user associated with this token
     * 
     * @return The associated user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this token
     * 
     * @param user The user to associate with this token
     */
    public void setUser(User user) {
        this.user = user;
    }
}