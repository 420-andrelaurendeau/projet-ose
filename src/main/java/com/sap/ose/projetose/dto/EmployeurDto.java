package com.sap.ose.projetose.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeurDto extends UtilisateurDto {

    private String entreprise;

    public EmployeurDto(String nom, String prenom, String phone, String email, String entreprise) {
        super(nom, prenom, phone, email);
        this.entreprise = entreprise;
    }
}
