package com.sap.ose.projetose.dto;

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
    private List<Long> internshipIds;

    public StudentDto(String nom, String prenom, String phone, String email, String matricule, long programme, List<Long> cvIds, List<Long> internshipIds) {
        super(nom, prenom, phone, email);
        this.matricule = matricule;
        this.programId = programme;
        this.cvIds = cvIds;
        this.internshipIds = internshipIds;
    }

    public StudentDto(Student student) {
        super(student.getLastName(), student.getFirstName(), student.getPhoneNumber(), student.getEmail());
        this.matricule = student.getMatricule();
        this.programId = student.getProgram().getId();
        this.cvIds = student.getCvList() == null ? null : student.getCvList().stream().map(File::getId).toList();
        this.internshipIds = student.getInternshipApplications() == null ? null : student.getInternshipApplications().stream().map(InternshipApplication::getId).toList();
    }

    @Override
    public User toNewUser() {
        return new Student(getLastName(), getFirstName(), getPhoneNumber(), getEmail(), getMatricule(), (Program) null,null,null);
    }

}
