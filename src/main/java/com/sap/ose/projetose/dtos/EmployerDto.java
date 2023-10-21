package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.models.Employer;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerDto extends UserDto {
    private String enterprise;
    private long studyProgramId;

    public EmployerDto(Employer employer) {
        super(employer);
        this.enterprise = employer.getEnterprise();
        this.studyProgramId = employer.getStudyProgram().getId();
    }
}
