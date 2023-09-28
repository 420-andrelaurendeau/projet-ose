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
public class InternshipmanagerDto extends UtilisateurDto {

    private long id;
    private int programmeId;

    public InternshipmanagerDto(String nom, String prenom, String phone, String email, long id, int programmeId) {
        super(nom, prenom, phone, email);
        this.id = id;
        this.programmeId = programmeId;
    }

    public InternshipmanagerDto(long id) {
        this.id = id;
    }

    public Internshipmanager fromDto() {
        return new Internshipmanager(getNom(), getPrenom(), getPhone(), getEmail(), null, null);
    }
}
