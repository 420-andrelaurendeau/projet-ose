package com.sap.ose.projetose.dto.auth;

import com.sap.ose.projetose.modeles.Internshipmanager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipmanagerAuthDto extends UtilisateurAuthDto {

    private long programme_id;

    public InternshipmanagerAuthDto(String nom, String prenom, String phone, String email,String password, long programme_id) {
        super( nom, prenom, phone, email,password);
        this.programme_id = programme_id;
    }

    public InternshipmanagerAuthDto(long id, String nom, String prenom, String phone, String email,String password, long programme_id) {
        super(id, nom, prenom, phone, email, password);
        this.programme_id = programme_id;
    }

    public InternshipmanagerAuthDto(Internshipmanager internshipmanager){
        super(internshipmanager.getId(), internshipmanager.getNom(), internshipmanager.getPrenom(), internshipmanager.getPhone(), internshipmanager.getEmail(), internshipmanager.getPassword());
        this.programme_id = internshipmanager.getProgramme().getId();
    }

    public Internshipmanager fromDto() {
        return new Internshipmanager(getId(),getNom(), getPrenom(), getPhone(), getEmail(), getPassword(), null);
    }
}
