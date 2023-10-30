package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.annotations.InternshipOfferExists;
import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipCandidatesDto {
    private long id;
    @UserExists
    private long candidateId;
    @InternshipOfferExists
    private long internshipOfferDtoId;
    @FileExists
    private List<Long> fileTransferDtosId;

    public InternshipCandidatesDto(InternshipCandidates internshipCandidates) {
        this.id = internshipCandidates.getId();
        setCandidateId(internshipCandidates.getCandidate().getId());
        setInternshipOfferDtoId(internshipCandidates.getInternOffer().getId());
        setFileTransferDtosId(internshipCandidates.getFiles().stream().map(File::getId).toList());
    }
}
