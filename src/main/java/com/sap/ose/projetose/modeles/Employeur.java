package com.sap.ose.projetose.modeles;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("EMPLOYEUR")
public class Employeur extends Utilisateur {
    @Column(unique = true)
    private String entreprise;
    private int programme;

    public Employeur(int id, String nom, String prenom, String email, String phone, String password, String entreprise, int programme) {
        super(id, nom, prenom, email, phone, password);
        this.entreprise = entreprise;
        this.programme = programme;
    }

    public Employeur(String nom, String prenom, String email, String phone, String password, String entreprise, int programme) {
        super(nom, prenom, email, phone, password);
        this.entreprise = entreprise;
        this.programme = programme;
    }

    public Employeur(String nom, String prenom, String telephone, String email, String password, String entreprise) {
        super(nom, prenom, telephone, email, password);
        this.entreprise = entreprise;
    }
}
