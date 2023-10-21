package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto extends UserDto {
    private String matricule;
    private long programId;
    private List<Long> cvIds;
    private List<Long> applicationIds;

    public StudentDto(String lastName, String firstName, String phone, String email, String matricule, long programme, List<Long> cvIds, List<Long> applicationIds) {
        super(firstName, lastName, phone, email);
        this.matricule = matricule;
        this.programId = programme;
        this.cvIds = cvIds;
        this.applicationIds = applicationIds;
    }

    public StudentDto(Student student) {
        super(student.getFirstName(), student.getLastName(), student.getPhoneNumber(), student.getEmail());
        this.matricule = student.getMatricule();
        this.programId = student.getStudyProgram().getId();
        this.cvIds = student.getCvList() == null ? null : student.getCvList().stream().map(File::getId).toList();
        this.applicationIds = student.getInternshipApplications() == null ? null : student.getInternshipApplications().stream().map(InternshipApplication::getId).toList();
    }

}