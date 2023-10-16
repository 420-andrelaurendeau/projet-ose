package com.sap.ose.projetose.validators;


import com.sap.ose.projetose.annotations.InternshipOfferExists;
import com.sap.ose.projetose.repository.InternshipOfferRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InternshipOfferExistsValidator implements ConstraintValidator<InternshipOfferExists, Long> {
    private final InternshipOfferRepository internshipOfferRepository;

    @Override
    public boolean isValid(Long fileId, ConstraintValidatorContext context) {
        return internshipOfferRepository.existsById(fileId);
    }
}
