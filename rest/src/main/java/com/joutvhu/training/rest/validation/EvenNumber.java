package com.joutvhu.training.rest.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EvenNumberValidator.class)
public @interface EvenNumber {
    String message() default "The value must be an even number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
