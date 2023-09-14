package com.sap.ose.projetose.Employeur;

public class Employeur {
    private Long id;
    private String nom;
    private String prenom;
    private String nomEntreprise;
    private int telephone;
    private String email;
    private int documents;

    public Employeur() {
    }

    public Employeur(Long id, String nom, String prenom, String nomEntreprise, int telephone, String email, int documents) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nomEntreprise = nomEntreprise;
        this.telephone = telephone;
        this.email = email;
        this.documents = documents;
    }
}