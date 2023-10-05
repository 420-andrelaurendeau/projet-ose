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
    private long programmeId;

    public InternshipManagerDto(InternshipManager internshipmanager) {
        super(internshipmanager.getNom(), internshipmanager.getPrenom(), internshipmanager.getPhone(), internshipmanager.getEmail());
        this.id = internshipmanager.getId();
        this.programmeId = internshipmanager.getFormation().getId();
    }

    public InternshipManager fromDto() {
        return new InternshipManager(getNom(), getPrenom(), getPhone(), getEmail(), null, null);
    }

    @Override
    public User toUser() {
        return null;
    }
}
