package com.sap.ose.projetose.validators;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.repositories.FileRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class FileExistsValidator implements ConstraintValidator<FileExists, Long> {
    private final FileRepository fileRepository;

    @Override
    public boolean isValid(Long fileId, ConstraintValidatorContext context) {
        return fileRepository.existsById(fileId);
    }
}
