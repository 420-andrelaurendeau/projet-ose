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
    private long etudiant_id;
    private long interOfferJob_id;
    private List<Long> files_id;


    public InternshipCandidatesDto(long id, int etudiant_id, int interOfferJob_id, List<Long> files_id) {
        this.id = id;
        this.etudiant_id = etudiant_id;
        this.interOfferJob_id = interOfferJob_id;
        this.files_id = files_id;
    }

    public InternshipCandidatesDto(InternshipCandidates internshipCandidates) {
        this.id = internshipCandidates.getId();
        this.etudiant_id = internshipCandidates.getEtudiant().getId();
        this.interOfferJob_id = internshipCandidates.getInternshipOffer().getId();
        this.files_id = internshipCandidates.getFiles().isEmpty() ? new ArrayList<>() : internshipCandidates.getFiles().stream().map(File::getId).collect(Collectors.toList());
    }

    public InternshipCandidates fromDto() {
        return new InternshipCandidates(null,null, null);
    }


}
