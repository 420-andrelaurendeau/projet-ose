package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "UTILISATEUR")
@NoArgsConstructor
@AllArgsConstructor
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    private String nom;
    private String prenom;
    private String phone;
    @Column(unique = true)
    private String email;
    private String password;

    public Utilisateur(String nom, String prenom, String email, String phone, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}