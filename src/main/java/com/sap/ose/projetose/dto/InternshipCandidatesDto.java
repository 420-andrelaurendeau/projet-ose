package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.InternshipCandidates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternshipCandidatesDto {

    private long id;
    private long studentMatricule;
    private long internshipOfferId;
    private List<Long> filesId;

    public InternshipCandidatesDto(InternshipCandidates internshipCandidates) {
        this.id = internshipCandidates.getId();
        this.studentMatricule = internshipCandidates.getEtudiant().getId();
        this.internshipOfferId = internshipCandidates.getInternOffer().getId();
        this.filesId = internshipCandidates.getFiles().isEmpty() == true ? new ArrayList<>() : internshipCandidates.getFiles().stream().map(File::getId).collect(Collectors.toList());
    }

    public InternshipCandidates fromDto() {
        return new InternshipCandidates(null,null, null);
    }


}
