package com.sap.ose.projetose.validators;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.repository.FileEntityRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FileExistsValidator implements ConstraintValidator<FileExists, Long> {
    private final FileEntityRepository fileEntityRepository;

    @Override
    public boolean isValid(Long fileId, ConstraintValidatorContext context) {
        return fileEntityRepository.existsById(fileId);
    }
}
