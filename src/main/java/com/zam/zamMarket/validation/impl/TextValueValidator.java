package com.zam.zamMarket.validation.impl;

import com.zam.zamMarket.validation.annotations.TextValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TextValueValidator implements ConstraintValidator<TextValue, String> {

    @Override
    public void initialize(TextValue constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return value.matches("[a-zA-Z ]+");
    }

}
