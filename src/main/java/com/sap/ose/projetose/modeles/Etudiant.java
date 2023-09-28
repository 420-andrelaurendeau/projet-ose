package com.sap.ose.projetose.modeles;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ETUDIANT")
@Data
public class Etudiant extends Utilisateur{

    private String matricule;
    private String programme;
    private String cv;

    public Etudiant(String nom, String prenom, String telephone, String email, String password, String matricule, String programme) {
        super(nom, prenom, telephone, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = "";
    }
}
