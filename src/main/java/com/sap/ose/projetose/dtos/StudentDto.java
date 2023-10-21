package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class StudentDto extends UserDto {
    private String matricule;
    private List<Long> cvIds;
    private List<Long> applicationIds;

    public StudentDto(Student student) {
        super(student);
        this.matricule = student.getMatricule();
        this.cvIds = student.getCvList() == null ? null : student.getCvList().stream().map(File::getId).toList();
        this.applicationIds = student.getInternshipApplications() == null ? null : student.getInternshipApplications().stream().map(InternshipApplication::getId).toList();
    }

}
