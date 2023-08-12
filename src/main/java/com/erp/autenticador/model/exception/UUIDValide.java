package com.erp.autenticador.model.exception;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = UUidValidator.class)
@Documented
public @interface UUIDValide {

    String message() default "UUID formato invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
