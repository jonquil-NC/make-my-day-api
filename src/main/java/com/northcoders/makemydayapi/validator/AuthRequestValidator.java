package com.northcoders.makemydayapi.validator;

import com.northcoders.makemydayapi.dto.AuthRequestDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthRequestValidator implements Validator {
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
    
    @Override
    public boolean supports(Class<?> clazz) {
        return AuthRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthRequestDTO request = (AuthRequestDTO) target;

        Matcher emailMatcher = emailPattern.matcher(request.getEmail());
        Matcher passwordMatcher = passwordPattern.matcher(request.getPassword());

        if (!StringUtils.hasText(request.getFirstname())) {
            errors.rejectValue("firstname", "firstname.required", "First name is required");
        }

        if (!StringUtils.hasText(request.getLastname())) {
            errors.rejectValue("lastname", "lastname.required", "Last name is required");
        }

        if (!StringUtils.hasText(request.getEmail())) {
            errors.rejectValue("email", "email.required", "Email address is required");
        } else {
            if (!emailMatcher.matches()) {
                errors.rejectValue("email", "email.invalid", "Email address is invalid");
            }
        }


        if (!StringUtils.hasText(request.getPassword())) {
            errors.rejectValue("password", "password.required", "Password address is required");
        } else {
            if (!passwordMatcher.matches()) {
                errors.rejectValue("password", "password.invalid", "Password must be between 8 and 20 characters long, "
                        + "contain at least one lowercase letter, one uppercase letter, one digit, and one special character (@#$%^&+=).");
            }
        }
    }
}
