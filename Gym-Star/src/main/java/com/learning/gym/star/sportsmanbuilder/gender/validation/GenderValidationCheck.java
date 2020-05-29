package com.learning.gym.star.sportsmanbuilder.gender.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = GenderValidator.class)
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface GenderValidationCheck {

    String message() default "Inserted wrong gender character. Gender can be M or F.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
