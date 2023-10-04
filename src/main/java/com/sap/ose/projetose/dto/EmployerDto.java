package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerDto extends UserDto {

    private String entreprise;
    private long programme_id;

    public EmployerDto(String nom, String prenom, String phone, String email, String entreprise) {
        super(nom, prenom, phone, email);
        this.entreprise = entreprise;
    }

    public EmployerDto(Employer employer) {
        super(employer.getNom(), employer.getPrenom(), employer.getPhone(), employer.getEmail());
        this.entreprise = employer.getEntreprise();
        this.programme_id = employer.getFormation().getId();
    }
}
