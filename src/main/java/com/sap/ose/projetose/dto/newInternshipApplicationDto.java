package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.annotations.InternshipOfferExists;
import com.sap.ose.projetose.annotations.UserExists;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class newInternshipApplicationDto {
    @UserExists
    private long candidateId;
    @InternshipOfferExists
    private long internshipOfferDtoId;
    @NotNull
    @FileExists
    private List<Long> fileTransferDtosId;
}
