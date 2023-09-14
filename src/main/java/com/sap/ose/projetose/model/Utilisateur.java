package com.sap.ose.projetose.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;


@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "UTILISATEUR_TYPE")
@Data
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "util_generator")
    @SequenceGenerator(name = "util_generator", sequenceName = "util_seq", allocationSize = 10000)
    @Column(name = "ID")
    private int id;
    private String nom;
    private String prenom;
    private String phone;
    private String email;

    private String password;



    public Utilisateur(){

    }

    public Utilisateur(String nom, String prenom, String telephone, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = telephone;
        this.email = email;
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
    	this.password = password;
    }
}
