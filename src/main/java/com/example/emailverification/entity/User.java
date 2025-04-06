package com.example.emailverification.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entity class representing a user in the system.
 * Implements UserDetails interface to integrate with Spring Security for authentication.
 */
@Entity // Marks this class as a JPA entity
@Table(
        name = "users", // Specifies the table name in the database
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "username_unique", // Constraint name for database
                        columnNames = "username"  // Ensures username is unique
                ),
                @UniqueConstraint(
                        name = "email_unique",    // Constraint name for database
                        columnNames = "email"     // Ensures email is unique
                )

})
public class User implements UserDetails {

    /**
     * Primary key for the user entity
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Uses a database sequence for ID generation
    @Column(name = "id")
    private Long id;

    /**
     * User's first name
     */
    @Column(
            name = "first_name",
            nullable = false // First name is required
    )
    private String firstName;

    /**
     * User's last name
     */
    @Column(
            name = "last_name",
            nullable = false // Last name is required
    )
    private String lastName;

    /**
     * Username for authentication
     */
    @Column(
            name = "username",
            unique = true // Username must be unique
    )
    private String username;

    /**
     * Encrypted password for authentication
     */
    @Column(
            name = "password",
            nullable = false // Password is required
    )
    private String password;

    /**
     * User's email address for communication and verification
     */
    @Column(
            name = "email",
            unique = true // Email must be unique
    )
    private String email;

    /**
     * Flag indicating if the user's account is enabled
     * Accounts are disabled until email verification is complete
     */
    @Column(
            name = "enabled"
    )
    private boolean enabled;

    /**
     * Flag indicating if the user's account is locked
     * Locked accounts cannot authenticate
     */
    @Column(name = "locked")
    private boolean locked;

    /**
     * User's role for authorization purposes
     * Stored as a string representation of the Role enum
     */
    @Enumerated(EnumType.STRING) // Store enum as string in the database
    private Role role;


    /**
     * Returns the authorities granted to the user
     * Required by UserDetails interface for Spring Security
     * 
     * @return Collection of GrantedAuthority objects based on user's role
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Returns the password used for authentication
     * Required by UserDetails interface
     * 
     * @return The user's password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used for authentication
     * Required by UserDetails interface
     * 
     * @return The user's username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the user's account has expired
     * Required by UserDetails interface
     * 
     * @return true if the account is valid (non-expired), false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // Account expiration not implemented
    }

    /**
     * Indicates whether the user is locked or unlocked
     * Required by UserDetails interface
     * 
     * @return true if the account is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /**
     * Indicates whether the user's credentials have expired
     * Required by UserDetails interface
     * 
     * @return true if credentials are valid (non-expired), false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credential expiration not implemented
    }

    /**
     * Indicates whether the user is enabled or disabled
     * Required by UserDetails interface
     * 
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Default constructor required by JPA
     */
    public User() {
    }

    /**
     * Parameterized constructor for creating a new user with all fields
     * 
     * @param firstName User's first name
     * @param lastName User's last name
     * @param username Username for authentication
     * @param password Password for authentication
     * @param email User's email address
     * @param enabled Whether the account is enabled
     * @param locked Whether the account is locked
     * @param role User's role for authorization
     */
    public User(
            String firstName,
            String lastName,
            String username,
            String password,
            String email,
            boolean enabled,
            boolean locked,
            Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.locked = locked;
        this.role = role;
    }

    /**
     * Gets the user's ID
     * 
     * @return The user's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's ID
     * 
     * @param id The ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user's first name
     * 
     * @return The user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name
     * 
     * @param firstName The first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name
     * 
     * @return The user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name
     * 
     * @param lastName The last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the user's username
     * 
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the user's password
     * 
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's email
     * 
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email
     * 
     * @param email The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets whether the user's account is enabled
     * 
     * @param enabled true to enable the account, false to disable
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Checks if the user's account is locked
     * 
     * @return true if the account is locked, false otherwise
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Sets whether the user's account is locked
     * 
     * @param locked true to lock the account, false to unlock
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Gets the user's role
     * 
     * @return The user's role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the user's role
     * 
     * @param role The role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Returns a string representation of the user
     * Useful for debugging and logging
     * 
     * @return String representation of the user object
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", locked=" + locked +
                ", role=" + role +
                '}';
    }
}