package com.sap.ose.projetose.models;


import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    @Column(unique = true)
    private String matricule;

    @ManyToOne
    @JoinColumn
    private StudyProgram studyProgram;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<File> cvList;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<InternshipApplication> internshipApplications;

    public Student(long id, String lastName, String firstName, String phoneNumber, String email, String password, String matricule, StudyProgram studyProgram, List<File> cvList, List<InternshipApplication> internshipApplications) {
        super(id, lastName, firstName, phoneNumber, email, password);
        this.matricule = matricule;
        this.studyProgram = studyProgram;
        this.cvList = cvList;
        this.internshipApplications = internshipApplications;
    }

    public Student(String lastName, String firstName, String phoneNumber, String email, String matricule, StudyProgram studyProgram, List<File> cvList, List<InternshipApplication> internshipApplications) {
        super(lastName, firstName, phoneNumber, email);
        this.matricule = matricule;
        this.studyProgram = studyProgram;
        this.cvList = cvList;
        this.internshipApplications = internshipApplications;
    }

    public Student(long id, String lastName, String firstName, String phone, String email, String password, String matricule, StudyProgram studyProgram, List<InternshipApplication> internshipApplications) {
        super(id, lastName, firstName, phone, email, password);
        this.matricule = matricule;
        this.studyProgram = studyProgram;
        this.cvList = null;
        this.internshipApplications = internshipApplications;
    }

    public Student(String lastName, String firstName, String phone, String email, String password, String matricule, StudyProgram studyProgram, List<InternshipApplication> internshipApplications) {
        super(lastName, firstName, phone, email, password);
        this.matricule = matricule;
        this.studyProgram = studyProgram;
        this.cvList = null;
        this.internshipApplications = internshipApplications;
    }

        public Student(String lastName, String firstName, String phone, String email, String password, String matricule, StudyProgram studyProgram) {
        super(lastName, firstName, phone, email, password);
        this.matricule = matricule;
        this.studyProgram = studyProgram;
        this.cvList = null;
        this.internshipApplications = new ArrayList<>();
    }

    @Override
    public UserDto toUserDto() {
        return new StudentDto(this);
    }
}
