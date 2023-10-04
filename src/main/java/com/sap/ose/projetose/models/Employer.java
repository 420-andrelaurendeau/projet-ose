package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("EMPLOYEUR")
@Data
public class Employer extends User {

    @Column(unique = true)
    private String entreprise;
    @ManyToOne
    private Formation formation;


    public Employer(long id, String nom, String prenom, String email, String phone, String password, String entreprise, Formation formation) {
        super(id, nom, prenom, email, phone, password);
        this.entreprise = entreprise;
        this.formation = formation;
    }

    public Employer(String nom, String prenom, String email, String phone, String password, String entreprise, Formation formation) {
        super(nom, prenom, email, phone, password);
        this.entreprise = entreprise;
        this.formation = formation;
    }



}
