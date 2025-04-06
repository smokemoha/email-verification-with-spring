package com.example.emailverification.service;

import com.example.emailverification.entity.Token;
import com.example.emailverification.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class responsible for managing verification tokens.
 * This class provides methods to save and retrieve tokens used in the email verification process.
 */
@Service // Marks this class as a Spring service component for dependency injection
public class TokenService {
    
    // Repository for database operations related to tokens
    private final TokenRepository tokenRepository;

    /**
     * Constructor-based dependency injection for token repository
     * 
     * @param tokenRepository Repository for token data access
     */
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    /**
     * Finds a token by its string value
     * 
     * @param token The token string to search for
     * @return Optional containing the token if found, empty otherwise
     */
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    /**
     * Saves a token to the database
     * Used for both creating new tokens and updating existing ones
     * 
     * @param token The token entity to save
     */
    public void save(Token token) {
        tokenRepository.save(token);
    }
}