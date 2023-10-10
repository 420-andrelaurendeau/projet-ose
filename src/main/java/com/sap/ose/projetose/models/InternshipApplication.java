package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InternshipApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "etudiant_id")
    @ToString.Exclude
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "interOfferJob_id")
    private InternshipOffer internshipOffer;

    @OneToMany(mappedBy = "internshipCandidates", cascade = CascadeType.REMOVE)
    private List<File> files;

    public InternshipApplication(Student student, InternshipOffer internshipOffer, List<File> files) {
        this.student = student;
        this.internshipOffer = internshipOffer;
        this.files = files;
    }
}
