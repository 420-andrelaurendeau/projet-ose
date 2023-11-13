package com.sap.ose.projetose.dto.auth;

import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantAuthDto extends UtilisateurAuthDto {

    private String matricule;
    private long programme_id;
    private File cv;
    private List<Long> internships_ids;

    public EtudiantAuthDto(String nom, String prenom, String phone, String email,String password, String matricule, long programme_id, File cv, List<Long> internships_ids) {
        super(nom, prenom, phone, email,password);
        this.matricule = matricule;
        this.programme_id = programme_id;
        this.cv = cv;
        this.internships_ids = internships_ids;
    }

    public EtudiantAuthDto(Etudiant etudiant) {
        super(etudiant.getId(),etudiant.getNom(), etudiant.getPrenom(), etudiant.getPhone(), etudiant.getEmail(), etudiant.getPassword());
        this.matricule = etudiant.getMatricule();
        this.programme_id = etudiant.getProgramme().getId();
        this.cv = etudiant.getCv() == null ? null : etudiant.getCv();
        this.internships_ids = etudiant.getInternshipsCandidate() == null ? null : etudiant.getInternshipsCandidate().stream().map(InternshipCandidates::getId).toList();
    }

}
