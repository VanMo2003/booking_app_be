package com.example.booking_app.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD}) // where can use?
@Retention(RUNTIME) // được xử lý lúc nào
@Constraint(validatedBy = {DobValidator.class})
public @interface DobConstraint {
    String message() default "Your age must be greater than 16";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 16;
}
