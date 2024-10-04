package com.zam.zamMarket.validation.annotations;

import com.zam.zamMarket.validation.impl.TextValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TextValueValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface TextValue {

    String message() default "Must not contain numeric values";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};

}
