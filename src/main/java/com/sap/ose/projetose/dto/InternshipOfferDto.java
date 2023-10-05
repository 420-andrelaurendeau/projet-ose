package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.AssessmentState;
import com.sap.ose.projetose.models.InternshipOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternshipOfferDto {

    private long id;
    private String title;
    private String location;
    private String description;
    private double salaryByHour;
    private String startDate;
    private String endDate;
    private List<Long> internshipCandidates;
    private long programmeId;
    private FileDto file;
    private long employeurId;
    private String programmeNom;
    private String employeurPrenom;
    private String employeurNom;
    private String employeurEntreprise;
    private long offerReviewRequestId;
    private AssessmentState state;

    public InternshipOfferDto(InternshipOffer internshipOffer) {
        this.id = internshipOffer.getId();
        this.title = internshipOffer.getTitle();
        this.location = internshipOffer.getLocation();
        this.description = internshipOffer.getDescription();
        this.startDate =  internshipOffer.getStartDate().toString();
        this.endDate = internshipOffer.getEndDate().toString();
        this.internshipCandidates = internshipOffer.getInternshipCandidates() == null ? null : internshipOffer.getInternshipCandidates().stream().map(internshipCandidates -> internshipCandidates.getId()).collect(Collectors.toList());
        this.programmeId = internshipOffer.getFormation().getId();
        this.file = new FileDto(internshipOffer.getFile());
        this.employeurId = internshipOffer.getEmployer().getId();
        this.programmeNom = internshipOffer.getFormation().getNom();
        this.employeurPrenom = internshipOffer.getEmployer().getPrenom();
        this.employeurNom = internshipOffer.getEmployer().getNom();
        this.employeurEntreprise = internshipOffer.getEmployer().getEntreprise();
        this.state = internshipOffer.getState();
        this.offerReviewRequestId = internshipOffer.getOfferReviewRequest() == null ? 0 : internshipOffer.getOfferReviewRequest().getId();
    }

    public InternshipOfferDto(String title, String location, String description, double salaryByHour, String startDate, String endDate, List<InternshipCandidatesDto> internshipCandidates, long programmeId, FileDto file, AssessmentState state, long offerReviewRequestId) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipCandidates = internshipCandidates == null ? new ArrayList<>() : internshipCandidates.stream().map(InternshipCandidatesDto::getId).collect(Collectors.toList());
        this.programmeId = programmeId;
        this.file = file;
        this.state = state;
        this.offerReviewRequestId = offerReviewRequestId;
    }

    public InternshipOffer fromDto() {
        return new InternshipOffer(id, title, location, description, salaryByHour,  LocalDate.parse(startDate), LocalDate.parse(endDate), null , null, file.toFile(), null,state, null);
    }
}