package com.sap.ose.projetose.validators;

import com.sap.ose.projetose.annotations.StudyProgramExists;
import com.sap.ose.projetose.repositories.StudyProgramRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudyProgramExistsValidator implements ConstraintValidator<StudyProgramExists, Long> {
    private final StudyProgramRepository studyProgramRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return studyProgramRepository.existsById(value);
    }
}
