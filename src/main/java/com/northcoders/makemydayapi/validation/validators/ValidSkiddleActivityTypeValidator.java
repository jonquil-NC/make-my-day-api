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
        boolean isValidSkiddleActivityType = activityType.equals(ActivityType.FEST)
                || activityType.equals(ActivityType.LIVE)
                || activityType.equals(ActivityType.CLUB)
                || activityType.equals(ActivityType.DATE)
                || activityType.equals(ActivityType.THEATRE)
                || activityType.equals(ActivityType.COMEDY)
                || activityType.equals(ActivityType.EXHIB)
                || activityType.equals(ActivityType.KIDS)
                || activityType.equals(ActivityType.BARPUB)
                || activityType.equals(ActivityType.LGB)
                || activityType.equals(ActivityType.SPORT)
                || activityType.equals(ActivityType.ARTS);

        return isValidSkiddleActivityType;
    }
}