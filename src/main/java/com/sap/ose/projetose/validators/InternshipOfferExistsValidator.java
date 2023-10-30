package com.sap.ose.projetose.validators;


import com.sap.ose.projetose.annotations.InternshipOfferExists;
import com.sap.ose.projetose.repository.InternOfferRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InternshipOfferExistsValidator implements ConstraintValidator<InternshipOfferExists, Long> {
    private final InternOfferRepository internOfferRepository;

    @Override
    public boolean isValid(Long fileId, ConstraintValidatorContext context) {
        return internOfferRepository.existsById(fileId);
    }
}
