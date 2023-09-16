package com.sap.ose.projetose.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employeur {
    @Id
    private Long id;
    private String nom;
    private String prenom;
    private String nomEntreprise;
    private int telephone;
    private String email;
    private int documents;
cd
}