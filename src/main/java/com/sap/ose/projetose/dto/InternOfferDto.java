package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Etats;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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
    private List<Long> internshipCandidates;
    private long programmeId;
    private FileDto file;
    private long employeurId;
    private String programmeNom;
    private String employeurPrenom;
    private String employeurNom;
    private String employeurEntreprise;
    private long offerReviewRequestId;
    private State state;

    public InternOfferDto(InternOffer internOffer) {
        this.id = internOffer.getId();
        this.title = internOffer.getTitle();
        this.location = internOffer.getLocation();
        this.description = internOffer.getDescription();
        this.startDate =  internOffer.getStartDate().toString();
        this.endDate = internOffer.getEndDate().toString();
        this.internshipCandidates = internOffer.getInternshipCandidates() == null ? new ArrayList<>() : internOffer.getInternshipCandidates().stream().map(InternshipCandidates::getId).collect(Collectors.toList());
        this.programmeId = internOffer.getProgramme().getId();
        this.file = new FileDto(internOffer.getFile());
        this.employeurId = internOffer.getEmployeur().getId();
        this.programmeNom = internOffer.getProgramme().getNom();
        this.employeurPrenom = internOffer.getEmployeur().getPrenom();
        this.employeurNom = internOffer.getEmployeur().getNom();
        this.employeurEntreprise = internOffer.getEmployeur().getEntreprise();
        this.state = internOffer.getState();
        this.offerReviewRequestId = internOffer.getOfferReviewRequest() == null ? 0 : internOffer.getOfferReviewRequest().getId();
        this.salaryByHour = internOffer.getSalaryByHour();
    }

    public InternOfferDto(String title, String location, String description, double salaryByHour, String startDate, String endDate, List<InternshipCandidatesDto> internshipCandidates, long programmeId, FileDto file,State state, long offerReviewRequestId) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipCandidates = internshipCandidates == null ? new ArrayList<>() : internshipCandidates.stream().map(internshipCandidate -> internshipCandidate.getId()).collect(Collectors.toList());
        this.programmeId = programmeId;
        this.file = file;
        this.state = state;
        this.offerReviewRequestId = offerReviewRequestId;
    }

    public InternOffer fromDto() {
        if (this.internshipCandidates == null) {
            return new InternOffer(id, title, location, description, salaryByHour,  LocalDate.parse(startDate), LocalDate.parse(endDate), null , null, file.fromDto(), null,state, null);
        }
        return new InternOffer(id, title, location, description, salaryByHour,  LocalDate.parse(startDate), LocalDate.parse(endDate), null , null, file.fromDto(), null,state, null);
    }
}