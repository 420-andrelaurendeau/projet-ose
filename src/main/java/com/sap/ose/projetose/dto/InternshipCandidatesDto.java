package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.InternshipCandidates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternshipCandidatesDto {

    private long id;
    private long etudiant_id;
    private long interOfferJob_id;
    private List<FileDto> files;


    public InternshipCandidatesDto(long id, int etudiant_id, int interOfferJob_id, List<FileDto> files) {
        this.id = id;
        this.etudiant_id = etudiant_id;
        this.interOfferJob_id = interOfferJob_id;
        this.files = files;
    }

    public InternshipCandidatesDto(InternshipCandidates internshipCandidates) {
        this.id = internshipCandidates.getId();
        this.etudiant_id = internshipCandidates.getEtudiant().getId();
        this.interOfferJob_id = internshipCandidates.getInternOffer().getId();
        this.files = internshipCandidates.getFiles().stream().map(FileDto::new).collect(Collectors.toList());
    }

    public InternshipCandidates fromDto() {
        return new InternshipCandidates(null,null, files.stream().map(FileDto::fromDto).collect(Collectors.toList()));
    }

}
