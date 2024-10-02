package com.northcoders.makemydayapi.validation.validators;

import com.northcoders.makemydayapi.model.ActivityType;
import com.northcoders.makemydayapi.validation.constraints.ValidSkiddleActivityType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidSkiddleActivityTypeValidator implements ConstraintValidator<ValidSkiddleActivityType, ActivityType> {
    @Override
    public void initialize(ValidSkiddleActivityType constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ActivityType value, ConstraintValidatorContext constraintValidatorContext) {
        return isValidSkiddleActivityType(value);
    }

    private static boolean isValidSkiddleActivityType(ActivityType activityType) {
        boolean isValidSkiddleActivityType = activityType.equals(ActivityType.FESTIVAL)
                || activityType.equals(ActivityType.LIVE)
                || activityType.equals(ActivityType.DATING)
                || activityType.equals(ActivityType.LGBT)
                || activityType.equals(ActivityType.THEATRE)
                || activityType.equals(ActivityType.COMEDY)
                || activityType.equals(ActivityType.KIDS)
                || activityType.equals(ActivityType.ARTS)
                || activityType.equals(ActivityType.CLUB);

        return isValidSkiddleActivityType;
    }
}