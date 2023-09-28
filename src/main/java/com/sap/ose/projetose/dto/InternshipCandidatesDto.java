package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.InternshipCandidates;

import java.util.List;
import java.util.stream.Collectors;

public class InternshipCandidatesDto {

    private long id;
    private EtudiantDto etudiant;
    private InternOfferDto interOfferJob;
    private List<FileDto> files;


    public InternshipCandidatesDto(long id, EtudiantDto etudiant, InternOfferDto interOfferJob, List<FileDto> files) {
        this.id = id;
        this.etudiant = etudiant;
        this.interOfferJob = interOfferJob;
        this.files = files;
    }

    public InternshipCandidatesDto(InternshipCandidates internshipCandidates) {
        this.id = internshipCandidates.getId();
        this.etudiant = new EtudiantDto(internshipCandidates.getEtudiant());
        this.interOfferJob = new InternOfferDto(internshipCandidates.getInternOffer());
        this.files = internshipCandidates.getFiles().stream().map(FileDto::new).collect(Collectors.toList());
    }

    public InternshipCandidates fromDto() {
        return new InternshipCandidates(etudiant.fromDto(), interOfferJob.fromDto(), files.stream().map(FileDto::fromDto).collect(Collectors.toList()));
    }

}
