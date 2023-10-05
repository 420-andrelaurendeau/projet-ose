package com.sap.ose.projetose.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class UtilisateurDto {
private long id;
    private String nom;
    private String prenom;
    private String phone;
    private String email;

    public UtilisateurDto(int id, String nom, String prenom, String phone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }

    public UtilisateurDto(String nom, String prenom, String phone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
    }
}
