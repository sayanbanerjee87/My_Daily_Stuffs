package com.schneider_electric.dces.pricing.model;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * User: FDU3285
 * Date: 17/11/2014
 * Time: 14:21
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy=PercentageValidator.class)
public @interface PercentageValidation {
    String message() default "{com.schneider_eletric.constraints.Percentage.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
