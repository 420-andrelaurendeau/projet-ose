package com.sap.ose.projetose.annotations;

import com.sap.ose.projetose.validators.FileExistsValidator;
import com.sap.ose.projetose.validators.FilesExistValidator;
import com.sap.ose.projetose.validators.UserExistsValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserExistsValidator.class)
public @interface UserExists {
    String message() default "{user.nonexistent}";

    Class[] groups() default {};

    Class[] payload() default {};
}
