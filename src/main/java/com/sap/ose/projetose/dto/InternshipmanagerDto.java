package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.BaseModel;
import com.sap.ose.projetose.modeles.Internshipmanager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipmanagerDto extends UtilisateurDto {
    private Set<Long> programId;

    public InternshipmanagerDto(Internshipmanager internshipManager) {
        super(internshipManager);
        this.programId = internshipManager.getProgrammes().stream().map(BaseModel::getId).collect(Collectors.toSet());
    }
}
