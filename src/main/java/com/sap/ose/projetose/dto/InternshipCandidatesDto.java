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
    private EtudiantDto etudiant;
    private InternOfferDto internOfferJob;
    private List<FileDto> files;

    public InternshipCandidatesDto(InternshipCandidates internshipCandidates) {
        this.id = internshipCandidates.getId();
        this.etudiant = internshipCandidates.getEtudiant() == null ? null : new EtudiantDto(internshipCandidates.getEtudiant());
        this.internOfferJob = internshipCandidates.getInternOffer() == null ? null : new InternOfferDto(internshipCandidates.getInternOffer());
        this.files = internshipCandidates.getFiles() == null ? null : internshipCandidates.getFiles().stream().map(FileDto::new).toList();
    }

    public InternshipCandidates fromDto() {
        return new InternshipCandidates(etudiant.fromDto(),internOfferJob.fromDto(), files == null ? new ArrayList<>() : files.stream().map(FileDto::fromDto).toList());
    }


}
