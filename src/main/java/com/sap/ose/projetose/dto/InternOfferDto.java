package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Etats;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.InternOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<Long> internshipApplicationIds;
    private long programId;
    private FileDto file;
    private long employerId;
    private String programName;
    private String employerFirstName;
    private String employerLastName;
    private String employerEnterprise;
    private long offerReviewRequestId;
    private Etats state;

    public InternOfferDto(InternOffer internOffer) {
        this.id = internOffer.getId();
        this.title = internOffer.getTitle();
        this.location = internOffer.getLocation();
        this.description = internOffer.getDescription();
        this.startDate = internOffer.getStartDate().toString();
        this.endDate = internOffer.getEndDate().toString();
        this.internshipApplicationIds = internOffer.getInternshipCandidates() == null ? null : internOffer.getInternshipCandidates().stream().map(InternshipCandidates::getId).collect(Collectors.toList());
        this.programId = internOffer.getProgramme().getId();
        this.file = new FileDto(internOffer.getFile());
        this.employerId = internOffer.getEmployeur().getId();
        this.programName = internOffer.getProgramme().getNom();
        this.employerFirstName = internOffer.getEmployeur().getFirstName();
        this.employerLastName = internOffer.getEmployeur().getLastName();
        this.employerEnterprise = internOffer.getEmployeur().getEnterprise();
        this.state = internOffer.getState();
        this.offerReviewRequestId = internOffer.getOfferReviewRequest() == null ? 0 : internOffer.getOfferReviewRequest().getId();
    }
}