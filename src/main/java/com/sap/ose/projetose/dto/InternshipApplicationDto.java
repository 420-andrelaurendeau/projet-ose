package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.annotations.InternshipOfferExists;
import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.InternshipApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipApplicationDto {
    private long id;
    @UserExists
    private long candidateId;
    @InternshipOfferExists
    private long internshipOfferDtoId;
    @FileExists
    private List<Long> fileTransferDtosId;

    public InternshipApplicationDto(InternshipApplication internshipApplication) {
        this.id = internshipApplication.getId();
        setCandidateId(internshipApplication.getCandidate().getId());
        setInternshipOfferDtoId(internshipApplication.getInternshipOffer().getId());
        setFileTransferDtosId(internshipApplication.getFiles().stream().map(File::getId).toList());
    }
}
