package com.northcoders.makemydayapi.validation.validators;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.validation.constraints.ValidSkiddleActivityType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidSkiddleActivityTypeValidator implements ConstraintValidator<ValidSkiddleActivityType, OneOffActivityType> {
    @Override
    public void initialize(ValidSkiddleActivityType constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(OneOffActivityType value, ConstraintValidatorContext constraintValidatorContext) {
        return isValidSkiddleActivityType(value);
    }

    private static boolean isValidSkiddleActivityType(OneOffActivityType activityType) {
        boolean isValidSkiddleActivityType = activityType.equals(OneOffActivityType.FEST)
                || activityType.equals(OneOffActivityType.LIVE)
                || activityType.equals(OneOffActivityType.CLUB)
                || activityType.equals(OneOffActivityType.DATE)
                || activityType.equals(OneOffActivityType.THEATRE)
                || activityType.equals(OneOffActivityType.COMEDY)
                || activityType.equals(OneOffActivityType.EXHIB)
                || activityType.equals(OneOffActivityType.KIDS)
                || activityType.equals(OneOffActivityType.BARPUB)
                || activityType.equals(OneOffActivityType.LGB)
                || activityType.equals(OneOffActivityType.SPORT)
                || activityType.equals(OneOffActivityType.ARTS);

        return isValidSkiddleActivityType;
    }
}