package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtilisateurAuthDto {
    private long id;
    private String nom;
    private String prenom;
    private String phone;
    private String email;
    private String password;

    public UtilisateurAuthDto(long id, String nom, String prenom, String phone, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public UtilisateurAuthDto(String nom, String prenom, String phone, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}
