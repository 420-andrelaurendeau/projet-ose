package com.sap.ose.projetose.models;


import com.sap.ose.projetose.dtos.StudentDto;
import com.sap.ose.projetose.dtos.UserDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    @Column(unique = true)
    private String matricule;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<File> cvList;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<InternshipApplication> internshipApplications;

    public Student(String lastName, String firstName, String phone, String email, String password, StudyProgram studyProgram, String matricule) {
        super(lastName, firstName, phone, email, password, studyProgram);
        this.matricule = matricule;
        this.cvList = new ArrayList<>();
        this.internshipApplications = new ArrayList<>();
    }

    @Override
    public UserDto toDto() {
        return new StudentDto(this);
    }
}
