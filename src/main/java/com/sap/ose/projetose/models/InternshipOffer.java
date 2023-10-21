package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.InternshipOfferDto;
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

    @OneToMany(cascade = CascadeType.ALL)
    private List<InternshipApplication> internshipApplications;
    @ManyToOne
    @JoinColumn
    private StudyProgram studyProgram;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    private File file;
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Employer employer;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private OfferReviewRequest offerReviewRequest;
}