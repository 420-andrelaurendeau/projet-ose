package com.sap.ose.projetose.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeurDto {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String courriel;
    private String entreprise;
}
