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
public class EtudiantDto extends UtilisateurDto {
    private String matricule;
    private long programme_id;
    private List<Long> cv;
    private List<Long> internships_id;

    public EtudiantDto(String nom, String prenom, String phone, String email, String matricule, long programme, List<Long> cv, List<Long> internships_id) {
        super(nom, prenom, phone, email);
        this.matricule = matricule;
        this.programme_id = programme;
        this.cv = cv;
        this.internships_id = internships_id;
    }


    public EtudiantDto(Etudiant etudiant) {
        super(etudiant.getId(), etudiant.getNom(), etudiant.getPrenom(), etudiant.getPhone(), etudiant.getEmail());
        this.matricule = etudiant.getMatricule();
        this.programme_id = etudiant.getProgramme().getId();
        this.cv = etudiant.getCv() == null ? null : etudiant.getCv().stream().map(File::getId).toList();
    }

    public Etudiant fromDto() {
        return new Etudiant(getNom(), getPrenom(), getPhone(), getEmail(), getMatricule(), (Programme) null,null,null);
    }

    public Etudiant fromDtoWithId(){
        return new Etudiant(getId(),getNom(), getPrenom(), getPhone(), getEmail(), getMatricule(), getMatricule(),(Programme) null,null);}
}
