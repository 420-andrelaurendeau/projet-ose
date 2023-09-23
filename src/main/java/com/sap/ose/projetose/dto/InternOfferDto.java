package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.InternOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternOfferDto {

    private long id;
    private String title;
    private String location;
    private String description;
    private double salaryByHour;
    private String startDate;
    private String endDate;
    private List<InternshipCandidatesDto> internshipCandidates;
    private ProgrammeDto programme;
    private FileDto file;


    public InternOfferDto(InternOffer internOffer) {
        this.id = internOffer.getId();
        this.title = internOffer.getTitle();
        this.location = internOffer.getLocation();
        this.description = internOffer.getDescription();
        this.startDate =  internOffer.getStartDate().toString();
        this.endDate = internOffer.getEndDate().toString();
        this.internshipCandidates = internOffer.getInternshipCandidates() == null ? null : internOffer.getInternshipCandidates().stream().map(InternshipCandidatesDto::new).collect(Collectors.toList()) ;
        this.programme = new ProgrammeDto(internOffer.getProgramme());
        this.file = new FileDto(internOffer.getFile());


    }

    public InternOffer fromDto() {
        if (this.internshipCandidates == null) {
            return new InternOffer(title, location, description, salaryByHour,  LocalDate.parse(startDate), LocalDate.parse(endDate), null , programme.fromDto(), file.fromDto());
        }
        return new InternOffer(title, location, description, salaryByHour,  LocalDate.parse(startDate), LocalDate.parse(endDate), internshipCandidates.stream().map(InternshipCandidatesDto::fromDto).collect(Collectors.toList()) , programme.fromDto(), file.fromDto());
    }
}
