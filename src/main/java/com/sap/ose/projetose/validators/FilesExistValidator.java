package com.sap.ose.projetose.validators;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.repositories.FileRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class FilesExistValidator implements ConstraintValidator<FileExists, List<Long>> {
    private final FileRepository fileRepository;

    @Override
    public boolean isValid(List<Long> fileIds, ConstraintValidatorContext context) {
        return fileIds.stream().allMatch(fileRepository::existsById);
    }
}
