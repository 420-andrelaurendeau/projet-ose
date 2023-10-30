package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InternOffer extends BaseModel {
    private String title;
    private String location;
    @Column(columnDefinition="TEXT")
    private String description;
    private double salaryByHour;
    private LocalDate startDate;
    private LocalDate endDate;
    private Etats state;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<InternshipCandidates> internshipCandidates;
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Programme programme;
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn
    @ToString.Exclude
    private File file;
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Employeur employeur;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    @ToString.Exclude
    private OfferReviewRequest offerReviewRequest;
}