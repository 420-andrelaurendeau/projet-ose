package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.InternshipApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternshipCandidatesDto {
    private long id;
    private long studentMatricule;
    private long internshipOfferId;
    private List<FileDto> files;

    public InternshipCandidatesDto(InternshipApplication internshipApplication) {
        this.id = internshipApplication.getId();
        this.studentMatricule = internshipApplication.getEtudiant().getId();
        this.internshipOfferId = internshipApplication.getInternshipOffer().getId();
        this.files = internshipApplication.getFiles().isEmpty() ? new ArrayList<>() : internshipApplication.getFiles().stream().map(FileDto::new).toList();
    }

    public InternshipApplication fromDto() {
        return new InternshipApplication(null,null, null);
    }
}
