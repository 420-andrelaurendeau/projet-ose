package com.sap.ose.projetose.modeles;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String status;
    private State state;

    @OneToMany(cascade = CascadeType.ALL)
    private List<InternshipCandidates> internshipCandidates;

    @ManyToOne()
    @JoinColumn
    private Programme programme;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    private File file;

    @ManyToOne()
    @JoinColumn
    private Employeur employeur;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private OfferReviewRequest offerReviewRequest;

    public InternOffer(String title,
                       String location,
                       String description,
                       double salaryByHour,
                       LocalDate startDate,
                       LocalDate endDate,
                       List<InternshipCandidates> internshipCandidates,
                       Programme programme,
                       File files,
                       Employeur employeur,
                       State state,
                       OfferReviewRequest offerReviewRequest) {
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

    public InternOffer(InternOffer internOffer) {
        this.id = internOffer.getId();
        this.title = internOffer.getTitle();
        this.location = internOffer.getLocation();
        this.description = internOffer.getDescription();
        this.salaryByHour = internOffer.getSalaryByHour();
        this.startDate = internOffer.getStartDate();
        this.endDate = internOffer.getEndDate();
        this.internshipCandidates = internOffer.getInternshipCandidates();
        this.programme = internOffer.getProgramme();
        this.file = internOffer.getFile();
        this.employeur = internOffer.getEmployeur();
        this.state = internOffer.getState();
        this.offerReviewRequest = internOffer.getOfferReviewRequest();
    }

    @Override
    public String toString() {
        return "InternOffer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", salaryByHour=" + salaryByHour +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", state=" + state +
                ", internshipCandidates=" + internshipCandidates +
                ", programme=" + programme +
                ", file=" + file +
                ", employeur=" + employeur +
                '}';
    }
}