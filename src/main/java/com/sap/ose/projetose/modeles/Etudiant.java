package com.sap.ose.projetose.modeles;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ETUDIANT")
@Data
public class Etudiant extends Utilisateur{
    private String matricule;

    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Programme programme;

    private String cv;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "etudiant_id")
    private List<InternshipCandidates> internshipsCandidate;


    public Etudiant(String nom, String prenom, String telephone, String email, String password, String matricule, Programme programme, List<InternshipCandidates> internshipsCandidate) {
        super(nom, prenom, telephone, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = "";
        this.internshipsCandidate = internshipsCandidate;
    }

    public Etudiant(long id,String nom, String prenom, String telephone, String email, String password, String matricule, Programme programme, List<InternshipCandidates> internshipsCandidate) {
        super(id, nom, prenom, telephone, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = "";
        this.internshipsCandidate = internshipsCandidate;
    }
}
