package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Employeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeurDto extends UtilisateurDto {

    private String entreprise;
    private long programme_id;

    public EmployeurDto(String nom, String prenom, String phone, String email, String entreprise) {
        super(nom, prenom, phone, email);
        this.entreprise = entreprise;
    }

    public EmployeurDto(Employeur employeur){
        super(employeur.getNom(),employeur.getPrenom(),employeur.getPhone(),employeur.getEmail());
        this.entreprise = employeur.getEntreprise();
        this.programme_id = employeur.getProgramme().getId();
    }
}
