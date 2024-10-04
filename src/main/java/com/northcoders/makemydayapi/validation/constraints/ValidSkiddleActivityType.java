package com.northcoders.makemydayapi.validation.constraints;

import com.northcoders.makemydayapi.validation.validators.ValidSkiddleActivityTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ValidSkiddleActivityTypeValidator.class)
public @interface ValidSkiddleActivityType {
    String message() default "Invalid Skiddle Activity Type!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}