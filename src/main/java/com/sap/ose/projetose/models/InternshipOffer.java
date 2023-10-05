package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InternshipOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String location;
    private String description;
    private double salaryByHour;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private ApprovalStatus state;

    @OneToMany(mappedBy = "internOffer", cascade = CascadeType.ALL)
    private List<InternshipApplication> internshipCandidates;

    @ManyToOne()
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne()
    @JoinColumn(name = "employeur_id")
    @ToString.Exclude
    private Employer employer;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "offerReviewRequest_id")
    private OfferReviewRequest offerReviewRequest;

    public InternshipOffer(String title,
                           String location,
                           String description,
                           double salaryByHour,
                           LocalDate startDate,
                           LocalDate endDate,
                           List<InternshipApplication> internshipCandidates,
                           Program program,
                           File files,
                           Employer employer,
                           ApprovalStatus state,
                           OfferReviewRequest offerReviewRequest) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipCandidates = internshipCandidates;
        this.program = program;
        this.file = files;
        this.employer = employer;
        this.state = state;
        this.offerReviewRequest = offerReviewRequest;
    }

    public InternshipOffer(long id,
                           String title,
                           String location,
                           String description,
                           double salaryByHour,
                           LocalDate startDate,
                           LocalDate endDate,
                           List<InternshipApplication> internshipCandidates,
                           Program program,
                           File files,
                           Employer employer,
                           ApprovalStatus state,
                           OfferReviewRequest offerReviewRequest) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipCandidates = internshipCandidates;
        this.program = program;
        this.file = files;
        this.employer = employer;
        this.state = state;
        this.offerReviewRequest = offerReviewRequest;
    }
}