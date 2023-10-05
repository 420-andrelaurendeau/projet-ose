package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.InternshipManager;
import com.sap.ose.projetose.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipManagerDto extends UserDto {

    private long id;
    private long programId;

    public InternshipManagerDto(String nom, String prenom, String phone, String email, long id, int programId) {
        super(nom, prenom, phone, email);
        this.id = id;
        this.programId = programId;
    }

    public InternshipManagerDto(InternshipManager internshipmanager) {
        super(internshipmanager.getLastName(), internshipmanager.getFirstName(), internshipmanager.getPhoneNumber(), internshipmanager.getEmail());
        this.id = internshipmanager.getId();
        this.programId = internshipmanager.getProgram().getId();
    }

    public InternshipManager fromDto() {
        return new InternshipManager(getLastName(), getFirstName(), getPhoneNumber(), getEmail(), null, null);
    }

    @Override
    public User toNewUser() {
        return new InternshipManager(id, getLastName(), getFirstName(), getPhoneNumber(), getEmail(), null, null);
    }
}
