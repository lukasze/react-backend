package com.example.reactbackend.controller.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsNotSauronValidator.class)
public @interface IsNotSauron {

    String message() default "You shall not pass!";

    Class<?>[] groups() default {};

    Class <? extends Payload>[] payload() default {};
}
