package com.sap.ose.projetose.modeles;


import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    @ToString.Exclude
    private List<File> cv;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<InternshipCandidates> internshipsCandidate;

    public Etudiant(long id,String nom, String prenom, String telephone, String email, String password, String matricule, Programme programme,List<File> cv, List<InternshipCandidates> internshipsCandidate) {
        super(id, nom, prenom, telephone, Role.student, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = cv;
        this.internshipsCandidate = internshipsCandidate;
    }

    public Etudiant(String nom, String prenom, String phone, String email, String matricule, Programme programme,List<File> cv, List<InternshipCandidates> internshipsCandidate) {
        super(nom, prenom, phone,Role.student, email);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = cv;
        this.internshipsCandidate = internshipsCandidate;
    }

    public Etudiant(long id, String nom, String prenom, String phone, String email, String password, String matricule, Programme programme, List<InternshipCandidates> internshipsCandidate) {
        super(id, nom, prenom, phone,Role.student, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = null;
        this.internshipsCandidate = internshipsCandidate;
    }

    public Etudiant(String nom, String prenom, String phone, String email, String password, String matricule, Programme programme, List<InternshipCandidates> internshipsCandidate) {
        super(nom, prenom, phone,Role.student, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = null;
        this.internshipsCandidate = internshipsCandidate;
    }

    public Etudiant(String nom, String prenom, String phone, String email, String password, String matricule, Programme programme) {
        super(nom, prenom, phone,Role.student, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = null;
        this.internshipsCandidate = new ArrayList<>();
    }
}
