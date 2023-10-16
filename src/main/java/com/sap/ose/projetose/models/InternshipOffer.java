package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dto.InternshipOfferDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@ToString
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

    public InternshipOffer(String title,
                           String location,
                           String description,
                           double salaryByHour,
                           LocalDate startDate,
                           LocalDate endDate,
                           List<InternshipApplication> internshipApplications,
                           StudyProgram studyProgram,
                           File file,
                           Employer employer,
                           ApprovalStatus state,
                           OfferReviewRequest offerReviewRequest) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.salaryByHour = salaryByHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipApplications = internshipApplications;
        this.studyProgram = studyProgram;
        this.file = file;
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
                           List<InternshipApplication> internshipApplications,
                           StudyProgram studyProgram,
                           File file,
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
        this.internshipApplications = internshipApplications;
        this.studyProgram = studyProgram;
        this.file = file;
        this.employer = employer;
        this.state = state;
        this.offerReviewRequest = offerReviewRequest;
    }

    public InternshipOfferDto toDto() {
        return new InternshipOfferDto(this);
    }
}