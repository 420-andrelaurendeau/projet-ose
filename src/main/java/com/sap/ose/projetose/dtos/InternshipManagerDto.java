package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.models.BaseModel;
import com.sap.ose.projetose.models.InternshipManager;
import com.sap.ose.projetose.models.StudyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipManagerDto extends UserDto {
    private Set<Long> programId;

    public InternshipManagerDto(InternshipManager internshipManager) {
        super(internshipManager);
        this.programId = internshipManager.getStudyPrograms().stream().map(BaseModel::getId).collect(Collectors.toSet());
    }
}
