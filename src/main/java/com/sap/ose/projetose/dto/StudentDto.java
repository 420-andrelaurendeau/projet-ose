package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto extends UserDto {
    private String matricule;
    private long programmeId;
    private List<File> cv;
    private List<Long> internshipsId;

    public StudentDto(long id, String nom, String prenom, String phone, String email, String matricule, long programme, List<File> cv, List<Long> internshipsId) {
        super(id, nom, prenom, phone, email);
        this.matricule = matricule;
        this.programmeId = programme;
        this.cv = cv;
        this.internshipsId = internshipsId;
    }

    public StudentDto(String nom, String prenom, String phone, String email, String matricule, long programme, List<File> cv, List<Long> internshipsId) {
        super(nom, prenom, phone, email);
        this.matricule = matricule;
        this.programmeId = programme;
        this.cv = cv;
        this.internshipsId = internshipsId;
    }

    public User toUser() {
        // FIXME: Remove empties
        return new Student(
                getId(),
                getNom(),
                getPrenom(),
                getPhone(),
                getEmail(),
                "",
                getMatricule(),
                new Formation(),
                new ArrayList<>());
    }

    public static StudentDto fromStudent(Student student) {
        return new StudentDto(
                student.getNom(),
                student.getPrenom(),
                student.getPhone(),
                student.getEmail(),
                student.getMatricule(),
                student.getFormation().getId(),
                student.getCv(),
                student.getInternshipsCandidate().stream().map(InternshipApplication::getId).toList());
    }
}
