package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private AssessmentState state;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "interOfferJob_id")
    private List<InternshipCandidates> internshipCandidates;

    @OneToOne()
    @JoinColumn(name = "program_id")
    private Formation formation;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne()
    @JoinColumn(name = "employeur_id")
    private Employer employer;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "offerReviewRequest_id")
    private OfferReviewRequest offerReviewRequest;

    public InternshipOffer(String title,
                           String location,
                           String description,
                           double salaryByHour,
                           LocalDate startDate,
                           LocalDate endDate,
                           List<InternshipCandidates> internshipCandidates,
                           Formation formation,
                           File files,
                           Employer employer,
                           AssessmentState state,
                           OfferReviewRequest offerReviewRequest) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipCandidates = internshipCandidates;
        this.formation = formation;
        this.file = files;
        this.employer = employer;
        this.state = state;
        this.offerReviewRequest = offerReviewRequest;
    }

    public InternshipOffer(long id, String title, String location, String description, double salaryByHour, LocalDate startDate, LocalDate endDate, List<InternshipCandidates> internshipCandidates, Formation formation, File file, Employer employer, AssessmentState state, OfferReviewRequest offerReviewRequest) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipCandidates = internshipCandidates;
        this.formation = formation;
        this.file = file;
        this.employer = employer;
        this.state = state;
        this.offerReviewRequest = offerReviewRequest;
    }
}