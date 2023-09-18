package com.sap.ose.projetose.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class UtilisateurDto {
    private String nom;
    private String prenom;
    private String phone;
    private String email;
}
