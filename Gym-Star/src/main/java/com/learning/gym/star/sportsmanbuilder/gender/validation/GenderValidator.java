package com.learning.gym.star.sportsmanbuilder.gender.validation;

import com.learning.gym.star.sportsmanbuilder.gender.GenderEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class GenderValidator implements ConstraintValidator<GenderValidationCheck, GenderEnum> {

    @Override
    public boolean isValid(GenderEnum value, ConstraintValidatorContext context){
        return Arrays.asList(GenderEnum.values()).contains(value);
    }
}
