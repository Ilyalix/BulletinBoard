package com.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Validation {

    public static <T> void validation(T t) {
        ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();

        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> violations = validator.validate(t);

        if (violations.size() > 0) {
            throw new ConstraintViolationException(violations);
        }
    }
}


