package com.sap.ose.projetose.modeles;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ETUDIANT")
@Data
public class Etudiant extends Utilisateur{
    @Column(unique = true)
    private String matricule;

    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Programme programme;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.REMOVE)
    private List<File> cv;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<InternshipCandidates> internshipsCandidate;

    public Etudiant(long id,String nom, String prenom, String telephone, String email, String password, String matricule, Programme programme,List<File> cv, List<InternshipCandidates> internshipsCandidate) {
        super(id, nom, prenom, telephone, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = cv;
        this.internshipsCandidate = internshipsCandidate;
    }

    public Etudiant(String nom, String prenom, String telephone, String email, String matricule, Programme programme,List<File> cv, List<InternshipCandidates> internshipsCandidate) {
        super(nom, prenom, telephone, email);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = cv;
        this.internshipsCandidate = internshipsCandidate;
    }

    public Etudiant(long id, String nom, String prenom, String phone, String email, String password, String matricule, Programme programme, List<InternshipCandidates> internshipsCandidate) {
        super(id, nom, prenom, phone, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = null;
        this.internshipsCandidate = internshipsCandidate;
    }

    public Etudiant(String nom, String prenom, String phone, String email, String password, String matricule, Programme programme, List<InternshipCandidates> internshipsCandidate) {
        super(nom, prenom, phone, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = null;
        this.internshipsCandidate = internshipsCandidate;
    }

        public Etudiant(String nom, String prenom, String phone, String email, String password, String matricule, Programme programme) {
        super(nom, prenom, phone, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = null;
        this.internshipsCandidate = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "matricule='" + matricule + '\'' +
                ", programme=" + programme +
                ", cv=" + cv +
                '}';
    }
}
