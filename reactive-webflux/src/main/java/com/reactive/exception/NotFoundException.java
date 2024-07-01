package com.reactive.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long userId) {
        super("Department not found with id: " + userId);
    }
}
