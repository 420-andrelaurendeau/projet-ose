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
    private int programmeId;
    private FileDto file;
    private long employeurId;
    private String programmeNom;
    private String employeurPrenom;
    private String employeurNom;
    private String employeurEntreprise;

    private State state;

    public InternOfferDto(InternOffer internOffer) {
        this.id = internOffer.getId();
        this.title = internOffer.getTitle();
        this.location = internOffer.getLocation();
        this.description = internOffer.getDescription();
        this.startDate =  internOffer.getStartDate().toString();
        this.endDate = internOffer.getEndDate().toString();
        this.internshipCandidates = internOffer.getInternshipCandidates() == null ? null : internOffer.getInternshipCandidates().stream().map(InternshipCandidatesDto::new).collect(Collectors.toList());
        this.programmeId = internOffer.getProgramme().getId();
        this.file = new FileDto(internOffer.getFile());
        this.employeurId = internOffer.getEmployeur().getId();
        this.programmeNom = internOffer.getProgramme().getNom();
        this.employeurPrenom = internOffer.getEmployeur().getPrenom();
        this.employeurNom = internOffer.getEmployeur().getNom();
        this.employeurEntreprise = internOffer.getEmployeur().getEntreprise();
        this.state = internOffer.getState();
    }

    public InternOfferDto(String title, String location, String description, double salaryByHour, String startDate, String endDate, List<InternshipCandidatesDto> internshipCandidates, int programmeId, FileDto file,State state) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipCandidates = internshipCandidates;
        this.programmeId = programmeId;
        this.file = file;
        this.state = state;
    }

    public InternOffer fromDto() {
        if (this.internshipCandidates == null) {
            return new InternOffer(title, location, description, salaryByHour,  LocalDate.parse(startDate), LocalDate.parse(endDate), null , null, file.fromDto(), null,state);
        }
        return new InternOffer(title, location, description, salaryByHour,  LocalDate.parse(startDate), LocalDate.parse(endDate), internshipCandidates.stream().map(InternshipCandidatesDto::fromDto).collect(Collectors.toList()) , null, file.fromDto(), null,state);
    }
}