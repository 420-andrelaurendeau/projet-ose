package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.InternshipApplication;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.ApprovalStatus;
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
    private List<Long> internshipApplicationIds;
    private long programId;
    private FileDto file;
    private long employerId;
    private String programNom;
    private String employerPrenom;
    private String employerNom;
    private String employerEntreprise;
    private long offerReviewRequestId;
    private ApprovalStatus state;

    public InternshipOfferDto(InternshipOffer internshipOffer) {
        this.id = internshipOffer.getId();
        this.title = internshipOffer.getTitle();
        this.location = internshipOffer.getLocation();
        this.description = internshipOffer.getDescription();
        this.startDate =  internshipOffer.getStartDate().toString();
        this.endDate = internshipOffer.getEndDate().toString();
        this.internshipApplicationIds = internshipOffer.getInternshipCandidates() == null ? null : internshipOffer.getInternshipCandidates().stream().map(InternshipApplication::getId).collect(Collectors.toList());
        this.programId = internshipOffer.getProgram().getId();
        this.file = new FileDto(internshipOffer.getFile());
        this.employerId = internshipOffer.getEmployer().getId();
        this.programNom = internshipOffer.getProgram().getNom();
        this.employerPrenom = internshipOffer.getEmployer().getFirstName();
        this.employerNom = internshipOffer.getEmployer().getLastName();
        this.employerEntreprise = internshipOffer.getEmployer().getEntreprise();
        this.state = internshipOffer.getState();
        this.offerReviewRequestId = internshipOffer.getOfferReviewRequest() == null ? 0 : internshipOffer.getOfferReviewRequest().getId();
    }

    public InternshipOfferDto(String title, String location, String description, double salaryByHour, String startDate, String endDate, List<InternshipApplicationDto> internshipApplicationIds, long programmeId, FileDto file, ApprovalStatus state, long offerReviewRequestId) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipApplicationIds = internshipApplicationIds == null ? new ArrayList<>() : internshipApplicationIds.stream().map(internshipCandidate -> internshipCandidate.getId()).collect(Collectors.toList());
        this.programId = programmeId;
        this.file = file;
        this.state = state;
        this.offerReviewRequestId = offerReviewRequestId;
    }

    public InternshipOffer fromDto() {
        return new InternshipOffer(id, title, location, description, salaryByHour,  LocalDate.parse(startDate), LocalDate.parse(endDate), null , null, file.fromDto(), null,state, null);
    }
}