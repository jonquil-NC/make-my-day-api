package com.northcoders.makemydayapi.validation.validators;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.validation.constraints.ValidTicketmasterActivityType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidTicketmasterActivityTypeValidator implements ConstraintValidator<ValidTicketmasterActivityType, OneOffActivityType> {

    @Override
    public void initialize(ValidTicketmasterActivityType constraintAnnotation) {

    }

    @Override
    public boolean isValid(OneOffActivityType value, ConstraintValidatorContext constraintValidatorContext) {
        return isValidTicketmasterActivityType(value);
    }

    private static boolean isValidTicketmasterActivityType(OneOffActivityType activityType) {
        boolean isValidSkiddleActivityType = activityType.equals(OneOffActivityType.SPORTS)
                || activityType.equals(OneOffActivityType.MUSIC)
                || activityType.equals(OneOffActivityType.CULTURAL);

        return isValidSkiddleActivityType;
    }

}
