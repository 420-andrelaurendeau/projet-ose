package com.sap.ose.projetose.validators;


import com.sap.ose.projetose.annotations.InternshipOfferExists;
import com.sap.ose.projetose.repositories.InternshipOfferRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InternshipOfferExistsValidator implements ConstraintValidator<InternshipOfferExists, Long> {
    private final InternshipOfferRepository internshipOfferRepository;

    @Override
    public boolean isValid(Long fileId, ConstraintValidatorContext context) {
        return internshipOfferRepository.existsById(fileId);
    }
}
