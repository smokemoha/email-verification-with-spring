package com.example.emailverification.entity;

/**
 * Enumeration representing user roles in the system.
 * Used for authorization and access control.
 */
public enum Role {
    /**
     * Administrator role with elevated privileges
     * Typically has access to all system features and management functions
     */
    ADMIN,
    
    /**
     * Standard user role with basic privileges
     * Typical role assigned to regular registered users
     */
    USER,
}