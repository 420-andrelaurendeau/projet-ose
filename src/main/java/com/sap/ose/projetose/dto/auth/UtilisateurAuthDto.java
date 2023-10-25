package com.sap.ose.projetose.dto.auth;

import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.modeles.Utilisateur;
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

    public UtilisateurAuthDto(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.nom = utilisateur.getNom();
        this.prenom = utilisateur.getPrenom();
        this.phone = utilisateur.getPhone();
        this.email = utilisateur.getEmail();
    }
}
