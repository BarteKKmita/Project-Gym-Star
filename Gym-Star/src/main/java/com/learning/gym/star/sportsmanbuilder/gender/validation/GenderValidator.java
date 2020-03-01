package com.learning.gym.star.sportsmanbuilder.gender.validation;

import com.learning.gym.star.sportsmanbuilder.gender.GenderChoose;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class GenderValidator implements ConstraintValidator<GenderValidationCheck, GenderChoose> {
    private static final String[] validGender = {"M", "F"};

    @Override
    public boolean isValid(GenderChoose value, ConstraintValidatorContext context){
        return Arrays.stream(validGender)
                .anyMatch(gender -> gender.equalsIgnoreCase(value.toString()));
    }
}
