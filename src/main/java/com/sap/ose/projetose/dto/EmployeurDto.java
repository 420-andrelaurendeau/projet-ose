package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Employeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeurDto extends UtilisateurDto {
    private String enterprise;

    public EmployeurDto(Employeur employeur) {
        super(employeur);
        this.enterprise = employeur.getEnterprise();
    }
}
