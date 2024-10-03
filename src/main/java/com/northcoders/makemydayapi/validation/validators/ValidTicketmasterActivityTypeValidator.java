package com.northcoders.makemydayapi.validation.validators;

import com.northcoders.makemydayapi.model.ActivityType;
import com.northcoders.makemydayapi.validation.constraints.ValidTicketmasterActivityType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidTicketmasterActivityTypeValidator implements ConstraintValidator<ValidTicketmasterActivityType, ActivityType> {

    @Override
    public void initialize(ValidTicketmasterActivityType constraintAnnotation) {

    }

    @Override
    public boolean isValid(ActivityType value, ConstraintValidatorContext constraintValidatorContext) {
        return isValidTicketmasterActivityType(value);
    }

    private static boolean isValidTicketmasterActivityType(ActivityType activityType){
        return activityType.equals(ActivityType.SPORTS);
    }

}