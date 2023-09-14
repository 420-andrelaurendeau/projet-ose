package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EtudiantDTO {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String courriel;
    private int matricule;

    public EtudiantDTO(Etudiant etudiant) {
        this.id = etudiant.getId();
        this.nom = etudiant.getNom();
        this.prenom = etudiant.getPrenom();
        this.email = etudiant.getCourriel();
        this.motDePasse = etudiant.getMotDePasse();
        this.courriel = etudiant.getCourriel();
        this.matricule = etudiant.getMatricule();
    }


    public Etudiant fromDto() {
        return new Etudiant(id, nom, prenom, courriel, motDePasse, matricule);
    }
}
