package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private String motDePasse;
    @Column(unique = true)
    private String courriel;
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "matricule_generator")
    private int matricule;

    public Etudiant(String nom, String prenom, String courriel, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.courriel = courriel;
        this.motDePasse = motDePasse;
    }
}
