package com.example.gymServer.user;

import com.example.gymServer.authorization.user.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidUser() {
        // Create a valid User object
        User user = User.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .phoneNumber("1234567890")
                .password("password123")
                .build();

        // Validate the user
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        
        // There should be no violations for a valid user
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidFirstName() {
        // First name is blank, this should trigger a violation
        User user = User.builder()
                .firstname("")
                .lastname("Doe")
                .email("john.doe@example.com")
                .phoneNumber("1234567890")
                .password("password123")
                .build();

        // Validate the user
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Assert there's at least one violation
        assertFalse(violations.isEmpty());
        // Check if the violation contains the specific error message
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("First name must not be blank")));
    }

    @Test
    void testInvalidEmail() {
        // Invalid email format
        User user = User.builder()
                .firstname("John")
                .lastname("Doe")
                .email("invalid-email")
                .phoneNumber("1234567890")
                .password("password123")
                .build();

        // Validate the user
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Email should be valid")));
    }

    @Test
    void testInvalidPhoneNumber() {
        // Phone number with too few digits
        User user = User.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .phoneNumber("12345")
                .password("password123")
                .build();

        // Validate the user
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Phone number must be between 10 and 15 characters")));
    }

    @Test
    void testInvalidPassword() {
        // Password with fewer than 6 characters
        User user = User.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .phoneNumber("1234567890")
                .password("123")
                .build();

        // Validate the user
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Password must be at least 6 characters")));
    }

}
