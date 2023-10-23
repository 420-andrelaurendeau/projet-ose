package com.sap.ose.projetose.models;

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
public class InternshipOffer extends BaseModel {
    private String title;
    private String location;
    private String description;
    private double salaryByHour;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private ApprovalStatus state;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<InternshipApplication> internshipApplications;
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private StudyProgram studyProgram;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    @ToString.Exclude
    private File file;
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Employer employer;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    @ToString.Exclude
    private OfferReviewRequest offerReviewRequest;
}