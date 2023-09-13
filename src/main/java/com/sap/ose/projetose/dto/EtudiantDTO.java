package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EtudiantDTO {
    private String nom;
    private String prenom;
    private String email;
    private String courriel;
    private int matricule;

    public EtudiantDTO(Etudiant etudiant) {
        this.nom = etudiant.getNom();
        this.prenom = etudiant.getPrenom();
        this.email = etudiant.getEmail();
        this.courriel = etudiant.getCourriel();
        this.matricule = etudiant.getMatricule();
    }
    public Etudiant fromDto() {
        return new Etudiant(nom, prenom, email, courriel, matricule);
    }
}
