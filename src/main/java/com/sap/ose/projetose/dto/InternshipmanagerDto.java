package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.InternshipManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipmanagerDto extends UserDto {

    private long id;
    private long programmeId;

    public InternshipmanagerDto(InternshipManager internshipmanager) {
        super(internshipmanager.getNom(), internshipmanager.getPrenom(), internshipmanager.getPhone(), internshipmanager.getEmail());
        this.id = internshipmanager.getId();
        this.programmeId = internshipmanager.getFormation().getId();
    }

    public InternshipManager fromDto() {
        return new InternshipManager(getNom(), getPrenom(), getPhone(), getEmail(), null, null);
    }
}
