package com.sap.ose.projetose.validators;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.repository.FileRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FilesExistValidator implements ConstraintValidator<FileExists, List<Long>> {
    private final FileRepository fileRepository;

    @Override
    public boolean isValid(List<Long> fileId, ConstraintValidatorContext context) {
        return fileId.stream().allMatch(fileRepository::existsById);
    }
}
