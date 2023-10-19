package com.sap.ose.projetose.modeles;

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
    private Etudiant etudiant;

    @ManyToOne()
    @JoinColumn(name = "interOfferJob_id")
    private InternOffer internOffer;

    @OneToMany(mappedBy = "internshipCandidates", cascade = CascadeType.REMOVE)
    private List<File> files;

    public InternshipCandidates(Etudiant etudiant, InternOffer internOffer, List<File> files) {
        this.etudiant = etudiant;
        this.internOffer = internOffer;
        this.files = files;
    }

    public InternshipCandidates(InternshipCandidates internshipCandidates) {
        this.etudiant = internshipCandidates.getEtudiant();
        this.internOffer = internshipCandidates.getInternOffer();
        this.files = internshipCandidates.getFiles();
    }
}
