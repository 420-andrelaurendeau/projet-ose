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
    private long programId;

    public InternshipManagerDto(long id, String lastName, String firstName, String phone, String email, int programId) {
        super(id, lastName, firstName, phone, email);
        this.programId = programId;
    }

    public InternshipManagerDto(InternshipManager internshipmanager) {
        super(internshipmanager.getFirstName(), internshipmanager.getLastName(), internshipmanager.getPhoneNumber(), internshipmanager.getEmail());
        this.programId = internshipmanager.getStudyProgram().getId();
    }

    public InternshipManager toInternshipManager() {
        return new InternshipManager(getLastName(), getFirstName(), getPhoneNumber(), getEmail(), null, null);
    }

    @Override
    public User toUser() {
        return new InternshipManager(getId(), getLastName(), getFirstName(), getPhoneNumber(), getEmail(), null, null);
    }
}
