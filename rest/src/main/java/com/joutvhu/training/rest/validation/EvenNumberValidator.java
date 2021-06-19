package com.joutvhu.training.rest.validation;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EvenNumberValidator implements ConstraintValidator<EvenNumber, Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value == null || (value & 1) == 0;
    }
}
