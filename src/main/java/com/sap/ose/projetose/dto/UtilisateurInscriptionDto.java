package com.sap.ose.projetose.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtilisateurInscriptionDto {
    private long id;
    private String nom;
    private String prenom;
    private String phone;
    private String email;
    private String password;

    public UtilisateurInscriptionDto(String nom, String prenom, String phone, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public UtilisateurInscriptionDto(long id, String nom, String prenom, String phone, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}
