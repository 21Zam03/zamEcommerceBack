package com.zam.zamMarket.validation.impl;

import com.zam.zamMarket.validation.annotations.NumericValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumericValueValidator implements ConstraintValidator<NumericValue, String> {

    @Override
    public void initialize(NumericValue constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return value.matches("\\d+");
    }

}
