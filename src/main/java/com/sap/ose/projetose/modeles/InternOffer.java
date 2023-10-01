package com.sap.ose.projetose.modeles;

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
public class InternOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String location;
    private String description;
    private double salaryByHour;
    private LocalDate startDate;
    private LocalDate endDate;
    private State state;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "internshipCandidates_id")
    private List<InternshipCandidates> internshipCandidates;

    @OneToOne()
    @JoinColumn(name = "program_id")
    private Programme programme;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne()
    @JoinColumn(name = "employeur_id")
    private Employeur employeur;

    @OneToOne
    @JoinColumn(name = "offerReviewRequest_id")
    private OfferReviewRequest offerReviewRequest;

    public InternOffer(String title, String location, String description, double salaryByHour, LocalDate startDate,
                       LocalDate endDate, List<InternshipCandidates> internshipCandidates,
                       Programme programme, File files, Employeur employeur, State state, OfferReviewRequest offerReviewRequest) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipCandidates = internshipCandidates;
        this.programme = programme;
        this.file = files;
        this.employeur = employeur;
        this.state = state;
        this.offerReviewRequest = offerReviewRequest;
    }

    public InternOffer(long id, String title, String location, String description, double salaryByHour, LocalDate startDate, LocalDate endDate, List<InternshipCandidates> internshipCandidates, Programme programme, File file, Employeur employeur, State state, OfferReviewRequest offerReviewRequest) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipCandidates = internshipCandidates;
        this.programme = programme;
        this.file = file;
        this.employeur = employeur;
        this.state = state;
        this.offerReviewRequest = offerReviewRequest;
    }
}