package com.sap.ose.projetose.models;


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
public class Student extends User {
    private String matricule;

    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Formation formation;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cv_id")
    private List<File> cv;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "etudiant_id")
    private List<InternshipCandidates> internshipsCandidate;


    public Student(String nom, String prenom, String telephone, String email, String password, String matricule, Formation formation, List<InternshipCandidates> internshipsCandidate) {
        super(nom, prenom, telephone, email, password);
        this.matricule = matricule;
        this.formation = formation;
        this.internshipsCandidate = internshipsCandidate;
    }

    public Student(long id, String nom, String prenom, String telephone, String email, String password, String matricule, Formation formation, List<InternshipCandidates> internshipsCandidate) {
        super(id, nom, prenom, telephone, email, password);
        this.matricule = matricule;
        this.formation = formation;
        this.internshipsCandidate = internshipsCandidate;
    }

    public void AddCV(File cv) {
        this.cv.add(cv);
    }
}
