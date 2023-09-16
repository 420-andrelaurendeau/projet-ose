package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ETUDIANT")
public class Etudiant extends Utilisateur {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int matricule;
    private int programme;
    private String cv;

    public Etudiant(int id, String nom, String prenom, String phone, String email, String password, int programme, String cv) {
        super(id, nom, prenom, phone, email, password);
        this.programme = programme;
        this.cv = cv;
    }

    public Etudiant(String nom, String prenom, String phone, String email, String password, int programme, String cv) {
        super(nom, prenom, phone, email, password);
        this.programme = programme;
        this.cv = cv;
    }
}
