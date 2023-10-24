package com.sap.ose.projetose.validators;

import com.sap.ose.projetose.annotations.StudyProgramExists;
import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserExistsValidator  implements ConstraintValidator<UserExists, Long> {
    UserRepository userRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return userRepository.existsById(value);
    }
}
