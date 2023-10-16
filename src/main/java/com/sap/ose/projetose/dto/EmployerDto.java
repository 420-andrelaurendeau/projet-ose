package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.models.StudyProgram;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.models.User;
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
