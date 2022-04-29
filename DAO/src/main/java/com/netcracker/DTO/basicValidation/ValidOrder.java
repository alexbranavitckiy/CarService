package com.netcracker.DTO.basicValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderValid.class)
public @interface ValidOrder {
 String message() default "";

 Class<?>[] groups() default {};

 Class<? extends Payload>[] payload() default {};
}