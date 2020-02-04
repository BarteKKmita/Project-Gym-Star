package com.learning.gym.star.sportsmanbuilder.gender.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<GenderValidationCheck, String> {
    private static final String[] validGender = {"M", "F"};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        for(String gender : validGender) {
            if (gender.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
