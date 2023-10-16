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

    public InternshipManagerDto(InternshipManager internshipManager) {
        super(internshipManager);
        this.programId = internshipManager.getStudyProgram().getId();
    }
}
