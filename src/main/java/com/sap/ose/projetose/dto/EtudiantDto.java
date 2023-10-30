package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.Etudiant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class EtudiantDto extends UtilisateurDto {
    private String matricule;
    private List<Long> cvIds;
    private List<Long> applicationIds;

    public EtudiantDto(Etudiant etudiant) {
        super(etudiant);
        this.matricule = etudiant.getMatricule();
        this.cvIds = etudiant.getCvList() == null ? null : etudiant.getCvList().stream().map(File::getId).toList();
        this.applicationIds = etudiant.getInternshipCandidates() == null ? null : etudiant.getInternshipCandidates().stream().map(InternshipCandidates::getId).toList();
    }
}
