package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.Programme;
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
    private List<Long> cv_ids;
    private List<Long> internships_ids;

    public EtudiantAuthDto(String nom, String prenom, String phone, String email,String password, String matricule, long programme_id, List<Long> cv_ids, List<Long> internships_ids) {
        super(nom, prenom, phone, email,password);
        this.matricule = matricule;
        this.programme_id = programme_id;
        this.cv_ids = cv_ids;
        this.internships_ids = internships_ids;
    }

    public EtudiantAuthDto(Etudiant etudiant) {
        super(etudiant.getId(),etudiant.getNom(), etudiant.getPrenom(), etudiant.getPhone(), etudiant.getEmail(), etudiant.getPassword());
        this.matricule = etudiant.getMatricule();
        this.programme_id = etudiant.getProgramme().getId();
        this.cv_ids = etudiant.getCv() == null ? null : etudiant.getCv().stream().map(File::getId).toList();
        this.internships_ids = etudiant.getInternshipsCandidate() == null ? null : etudiant.getInternshipsCandidate().stream().map(InternshipCandidates::getId).toList();
    }

}
