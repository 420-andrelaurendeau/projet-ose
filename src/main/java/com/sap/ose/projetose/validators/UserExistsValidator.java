package com.sap.ose.projetose.validators;

import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.repository.UtilisateurRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserExistsValidator  implements ConstraintValidator<UserExists, Long> {
    UtilisateurRepository utilisateurRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return utilisateurRepository.existsById(value);
    }
}
