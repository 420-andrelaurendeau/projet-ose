package com.sap.ose.projetose.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotBlankValidator implements ConstraintValidator<NotBlank, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.isBlank();
    }
}
