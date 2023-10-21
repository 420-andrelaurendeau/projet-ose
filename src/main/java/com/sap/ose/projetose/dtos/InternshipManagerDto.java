package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.models.InternshipManager;
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
