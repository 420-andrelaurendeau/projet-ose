package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.models.ApprovalStatus;
import com.sap.ose.projetose.models.InternshipApplication;
import com.sap.ose.projetose.models.InternshipOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private NewFileTransferDto file;
    private long employerId;
    private String programName;
    private String employerFirstName;
    private String employerLastName;
    private String employerEnterprise;
    private long offerReviewRequestId;
    private ApprovalStatus state;

    public InternshipOfferDto(InternshipOffer internshipOffer) {
        this.id = internshipOffer.getId();
        this.title = internshipOffer.getTitle();
        this.location = internshipOffer.getLocation();
        this.description = internshipOffer.getDescription();
        this.startDate = internshipOffer.getStartDate().toString();
        this.endDate = internshipOffer.getEndDate().toString();
        this.internshipApplicationIds = internshipOffer.getInternshipApplications() == null ? null : internshipOffer.getInternshipApplications().stream().map(InternshipApplication::getId).collect(Collectors.toList());
        this.programId = internshipOffer.getStudyProgram().getId();
        this.file = new NewFileTransferDto(internshipOffer.getFile());
        this.employerId = internshipOffer.getEmployer().getId();
        this.programName = internshipOffer.getStudyProgram().getNom();
        this.employerFirstName = internshipOffer.getEmployer().getFirstName();
        this.employerLastName = internshipOffer.getEmployer().getLastName();
        this.employerEnterprise = internshipOffer.getEmployer().getEnterprise();
        this.state = internshipOffer.getState();
        this.offerReviewRequestId = internshipOffer.getOfferReviewRequest() == null ? 0 : internshipOffer.getOfferReviewRequest().getId();
    }
}