package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Employeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeurDto extends UtilisateurDto {

    private String entreprise;

    public EmployeurDto(String nom, String prenom, String phone, String email, String entreprise) {
        super(nom, prenom, phone, email);
        this.entreprise = entreprise;
    }

    public EmployeurDto(Employeur employeur){
        super(employeur.getNom(),employeur.getPrenom(),employeur.getPhone(),employeur.getEmail());
        employeur.getEntreprise();
    }
}