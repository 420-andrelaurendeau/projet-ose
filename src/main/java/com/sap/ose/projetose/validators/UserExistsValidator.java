package com.sap.ose.projetose.validators;


import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserExistsValidator implements ConstraintValidator<UserExists, Long> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext context) {
        System.out.println("Hello World! the following user is not found: " + userId);
        return userRepository.existsById(userId);
    }
}
