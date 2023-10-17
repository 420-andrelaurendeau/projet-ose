package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.State;
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
    private EtudiantDtoWithId etudiant;
    private InternOfferDto internOfferJob;
    private List<FileDto> files;
    private State state;

    public InternshipCandidatesDto(InternshipCandidates internshipCandidates) {
        this.id = internshipCandidates.getId();
        this.etudiant = internshipCandidates.getEtudiant() == null ? null : new EtudiantDtoWithId(internshipCandidates.getEtudiant());
        this.internOfferJob = internshipCandidates.getInternOffer() == null ? null : new InternOfferDto(internshipCandidates.getInternOffer());
        this.files = internshipCandidates.getFiles() == null ? null : internshipCandidates.getFiles().stream().map(FileDto::new).toList();
        this.state = internshipCandidates.getState();
    }

    public static List<InternshipCandidatesDto> fromList(List<InternshipCandidates> internshipCandidates) {
        return internshipCandidates.stream().map(InternshipCandidatesDto::new).collect(Collectors.toList());
    }

    public InternshipCandidates fromDto() {
        return new InternshipCandidates(etudiant.fromDto(),internOfferJob.fromDto(), files == null ? new ArrayList<>() : files.stream().map(FileDto::fromDto).toList(), state == null ? State.PENDING : state);
    }


}
