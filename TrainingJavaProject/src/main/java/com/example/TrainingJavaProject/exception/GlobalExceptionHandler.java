package com.example.TrainingJavaProject.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        String message = ex.getMessage().toLowerCase();

        // Handle unique constraint violation for Users table
        if (message.contains("username_key")) {
            errors.put("username", "Username is already taken. " + message);
        } else if (message.contains("email_key")) {
            errors.put("email", "Email is already registered.");
        } else {
            errors.put("unknown", "Duplicate entry.");
        }
        return errors;
    }

}
