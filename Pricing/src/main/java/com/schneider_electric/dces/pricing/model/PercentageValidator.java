package com.schneider_electric.dces.pricing.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: FDU3285
 * Date: 17/11/2014
 * Time: 14:24
 */
public class PercentageValidator implements ConstraintValidator<PercentageValidation, Double> {
    @Override
    public void initialize(PercentageValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value == null || (value >= 0 && value <= 1);
    }
}
