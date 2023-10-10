package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.InternshipApplication;
import com.sap.ose.projetose.models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternshipApplicationDto {
    private long id;
    private StudentDto candidateDto;
    private InternshipOfferDto internshipOfferDto;
    private List<FileDto> fileDtos;

    public InternshipApplicationDto(InternshipApplication internshipApplication) {
        this.id = internshipApplication.getId();
        this.candidateDto = internshipApplication.getStudent() == null ? null : new StudentDto(internshipApplication.getStudent());
        this.internshipOfferDto = internshipApplication.getInternshipOffer() == null ? null : new InternshipOfferDto(internshipApplication.getInternshipOffer());
        this.fileDtos = internshipApplication.getFiles() == null ? null : internshipApplication.getFiles().stream().map(FileDto::new).toList();
    }

    public InternshipApplication toInternshipApplication() {
        return new InternshipApplication((Student) candidateDto.toUser(), internshipOfferDto.toInternshipOffer(), fileDtos == null ? new ArrayList<>() : fileDtos.stream().map(FileDto::toFile).toList());
    }
}
