package com.sap.ose.projetose.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantDto {
    private String nom;
    private String prenom;
    private String phone;
    private String email;
    private String matricule;
    private String programme;
    private String cv;

}
