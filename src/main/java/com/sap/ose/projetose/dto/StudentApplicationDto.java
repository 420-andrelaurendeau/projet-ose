package com.sap.ose.projetose.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentApplicationDto {

    private InternshipOfferDto appliedOffer;
    private List<FileDto> appliedFiles;
}
