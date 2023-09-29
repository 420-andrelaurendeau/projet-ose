package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Etudiant;
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
    private long programme_id;
    private String cv;

    public EtudiantDto(String nom, String prenom, String phone, String email, String matricule, long programme, String cv) {
        super(nom, prenom, phone, email);
        this.matricule = matricule;
        this.programme_id = programme;
        this.cv = cv;
    }

    public EtudiantDto(Etudiant etudiant) {
        super(etudiant.getNom(), etudiant.getPrenom(), etudiant.getPhone(), etudiant.getEmail());
        this.matricule = etudiant.getMatricule();
        this.programme_id = etudiant.getProgramme().getId();
        this.cv = etudiant.getCv();
    }

    public Etudiant fromDto() {
        return new Etudiant(getNom(), getPrenom(), getPhone(), getEmail(), this.matricule, this.cv,null);
    }

}
