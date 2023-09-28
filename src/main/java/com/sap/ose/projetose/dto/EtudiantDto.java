package com.sap.ose.projetose.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantDto extends UtilisateurDto {
    private String matricule;
    private String programme;
    private String cv;

    public EtudiantDto(String nom, String prenom, String phone, String email, String matricule, String programme, String cv) {
        super(nom, prenom, phone, email);
        this.matricule = matricule;
        this.programme = programme;
        this.cv = cv;
    }

}
