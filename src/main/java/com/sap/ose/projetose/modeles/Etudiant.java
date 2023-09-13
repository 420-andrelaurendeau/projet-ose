package com.sap.ose.projetose.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Etudiant {
    private String nom;
    private String prenom;
    private String email;
    private String courriel;
    @Id
    private int matricule;
}
