package com.northcoders.makemydayapi.validatortest;

import com.northcoders.makemydayapi.dto.AuthRequestDTO;
import com.northcoders.makemydayapi.validator.AuthRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthRequestValidatorTest {

    private AuthRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AuthRequestValidator();
    }

    @Test
    void validate_whenFirstnameIsEmpty_shouldReturnError() {
        AuthRequestDTO request = new AuthRequestDTO("", "Doe", "john.doe@example.com", "Password@123!");
        Errors errors = new BeanPropertyBindingResult(request, "request");

        validator.validate(request, errors);

        assertTrue(errors.hasErrors());
        assertEquals("First name is required", errors.getFieldError("firstname").getDefaultMessage());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    void validate_whenLastnameIsEmpty_shouldReturnError() {
        AuthRequestDTO request = new AuthRequestDTO("John", "   ", "john.doe@example.com", "Password@123!");
        Errors errors = new BeanPropertyBindingResult(request, "request");

        validator.validate(request, errors);

        assertTrue(errors.hasErrors());
        assertEquals("Last name is required", errors.getFieldError("lastname").getDefaultMessage());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    void validate_whenEmailIsEmpty_shouldReturnError() {
        AuthRequestDTO request = new AuthRequestDTO("John", "Doe", "", "Password@123!");
        Errors errors = new BeanPropertyBindingResult(request, "request");

        validator.validate(request, errors);

        assertTrue(errors.hasErrors());
        assertEquals("Email address is required", errors.getFieldError("email").getDefaultMessage());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    void validate_whenEmailIsInvalid_shouldReturnError() {
        AuthRequestDTO request = new AuthRequestDTO("John", "Doe", "invalid-email", "Password@123!");
        Errors errors = new BeanPropertyBindingResult(request, "request");

        validator.validate(request, errors);

        assertTrue(errors.hasErrors());
        assertEquals("Email address is invalid", errors.getFieldError("email").getDefaultMessage());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    void validate_whenPasswordIsInvalid_shouldReturnError() {
        AuthRequestDTO request = new AuthRequestDTO("John", "Doe", "john.doe@example.com", "short");
        Errors errors = new BeanPropertyBindingResult(request, "request");

        validator.validate(request, errors);

        assertTrue(errors.hasErrors());
        assertEquals("Password must be between 8 and 20 characters long, " +
                        "contain at least one lowercase letter, one uppercase letter, one digit, and one special character (@#$%^&+=).",
                errors.getFieldError("password").getDefaultMessage());

        assertEquals(1, errors.getErrorCount());
    }
}
