package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.annotations.InternshipOfferExists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentApplicationsDto {
    @InternshipOfferExists
    private InternshipOfferDto appliedOffer;
    @FileExists
    private List<FileTransferDto> appliedFiles;
}
