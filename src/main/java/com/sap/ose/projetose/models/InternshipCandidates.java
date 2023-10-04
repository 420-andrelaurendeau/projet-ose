package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternshipCandidates {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "etudiant_id")
    private Student etudiant;

    @OneToOne()
    @JoinColumn(name = "interOfferJob_id")
    private InternshipOffer internshipOffer;

    @OneToMany()
    private List<File> files;

    public InternshipCandidates(Student etudiant, InternshipOffer internshipOffer, List<File> files) {
        this.etudiant = etudiant;
        this.internshipOffer = internshipOffer;
        this.files = files;
    }
}
