package com.sap.ose.projetose.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Utilisateur {
    @Id
    @GeneratedValue
    private Long id;
    String nom;
    String email;
    String motDePasse;

    public void setId(Long id) {
        this.id = id;
    }

}
