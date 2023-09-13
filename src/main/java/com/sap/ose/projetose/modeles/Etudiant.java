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
public class Etudiant {
    private String nom;
    private String prenom;
    private String email;
    private String courriel;
    @Id
    private int matricule;
}
