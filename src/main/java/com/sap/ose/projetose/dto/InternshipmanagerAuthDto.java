package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Internshipmanager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipmanagerAuthDto extends UtilisateurDto {

    private long programme_id;

    public InternshipmanagerAuthDto(String nom, String prenom, String phone, String email, long programme_id) {
        super( nom, prenom, phone, email);
        this.programme_id = programme_id;
    }

    public InternshipmanagerAuthDto(long id, String nom, String prenom, String phone, String email, long programme_id) {
        super(id, nom, prenom, phone, email);
        this.programme_id = programme_id;
    }

    public Internshipmanager fromDto() {
        return new Internshipmanager(getNom(), getPrenom(), getPhone(), getEmail(), null, null);
    }
}
