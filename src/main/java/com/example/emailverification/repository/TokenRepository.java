package com.example.emailverification.repository;

import com.example.emailverification.entity.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface for Token entity operations.
 * Extends Spring Data's CrudRepository to provide standard CRUD operations.
 * Spring Data JPA will automatically implement this interface at runtime.
 */
public interface TokenRepository extends CrudRepository<Token, Long> {
    
    /**
     * Finds a verification token by its string value
     * Used during the email verification process to validate tokens
     * 
     * @param token The token string to search for
     * @return Optional containing the token entity if found, empty otherwise
     */
    Optional<Token> findByToken(String token);
}