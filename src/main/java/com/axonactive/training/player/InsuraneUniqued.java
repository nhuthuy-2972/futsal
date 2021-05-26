package com.axonactive.training.player;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueInsuraceValidator.class)
public @interface InsuraneUniqued {
    String message() default "com.axonactive.training.player.InsuranceUniqued=Insurance unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
