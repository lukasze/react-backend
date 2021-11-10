package com.example.reactbackend.controller.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsNotSauronValidator implements ConstraintValidator<IsNotSauron, String> {
    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !"sauron".equalsIgnoreCase(name);
    }
}
