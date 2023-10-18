package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeurAuthDto extends UtilisateurAuthDto {
    private String entreprise;
    private long programme_id;

    public EmployeurAuthDto(long id, String nom, String prenom, String phone, String email, String password, String entreprise, long programme_id) {
        super(id, nom, prenom, phone, email, password);
        this.entreprise = entreprise;
        this.programme_id = programme_id;
    }

    public EmployeurAuthDto(String nom, String prenom, String phone, String email, String password, String entreprise, long programme_id) {
        super(nom, prenom, phone, email, password);
        this.entreprise = entreprise;
        this.programme_id = programme_id;
    }
}