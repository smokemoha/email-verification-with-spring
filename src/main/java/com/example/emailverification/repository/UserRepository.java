package com.example.emailverification.repository;

import com.example.emailverification.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface for User entity operations.
 * Extends Spring Data's CrudRepository to provide standard CRUD operations.
 * Spring Data JPA will automatically implement this interface at runtime.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
    /**
     * Finds a user by their username
     * Used primarily for authentication and user lookup
     * 
     * @param username The username to search for
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a user by either username or email
     * Used during registration to check if a user already exists with the given credentials
     * 
     * @param username The username to search for
     * @param email The email to search for
     * @return Optional containing the user if found by either username or email, empty otherwise
     */
    Optional<User> findByUsernameOrEmail(String username, String email);
}