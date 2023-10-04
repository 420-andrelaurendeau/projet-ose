package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternshipCandidates;
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
    private String etudiant_matricule;
    private long interOfferJob_id;
    private List<Long> files_id;

    public InternshipCandidatesDto(InternshipCandidates internshipCandidates) {
        this.id = internshipCandidates.getId();
        this.etudiant_matricule = internshipCandidates.getEtudiant().getMatricule();
        this.interOfferJob_id = internshipCandidates.getInternOffer().getId();
        this.files_id = internshipCandidates.getFiles().isEmpty() ? new ArrayList<>() : internshipCandidates.getFiles().stream().map(File::getId).collect(Collectors.toList());
    }

    public InternshipCandidates fromDto() {
        return new InternshipCandidates(null,null, null);
    }


}
