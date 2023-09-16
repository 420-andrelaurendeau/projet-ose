package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EtudiantDTO {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String phone;
    private String password;
    private int matricule;
    private int programme;
    private String cv;

    public EtudiantDTO(Etudiant etudiant) {
        this.id = etudiant.getId();
        this.nom = etudiant.getNom();
        this.prenom = etudiant.getPrenom();
        this.email = etudiant.getEmail();
        this.password = etudiant.getPassword();
        this.phone = etudiant.getPhone();
        this.matricule = etudiant.getMatricule();
        this.programme = etudiant.getProgramme();
        this.cv = etudiant.getCv();
    }

    public Etudiant fromDto() {
        return new Etudiant(id, nom, prenom, phone, email, password, programme, cv);
    }
}
