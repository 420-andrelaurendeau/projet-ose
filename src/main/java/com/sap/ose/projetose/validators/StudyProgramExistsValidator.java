package com.sap.ose.projetose.validators;

import com.sap.ose.projetose.annotations.StudyProgramExists;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudyProgramExistsValidator implements ConstraintValidator<StudyProgramExists, Long> {
    private final ProgrammeRepository programmeRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return programmeRepository.existsById(value);
    }
}
