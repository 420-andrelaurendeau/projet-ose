package com.sap.ose.projetose.models;


import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ETUDIANT")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    @Column(unique = true)
    private String matricule;

    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Program program;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.REMOVE)
    private List<File> cvList;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<InternshipApplication> internshipApplications;

    public Student(long id, String nom, String prenom, String telephone, String email, String password, String matricule, Program program, List<File> cvList, List<InternshipApplication> internshipApplications) {
        super(id, nom, prenom, telephone, email, password);
        this.matricule = matricule;
        this.program = program;
        this.cvList = cvList;
        this.internshipApplications = internshipApplications;
    }

    public Student(String nom, String prenom, String telephone, String email, String matricule, Program program, List<File> cvList, List<InternshipApplication> internshipApplications) {
        super(nom, prenom, telephone, email);
        this.matricule = matricule;
        this.program = program;
        this.cvList = cvList;
        this.internshipApplications = internshipApplications;
    }

    public Student(long id, String nom, String prenom, String phone, String email, String password, String matricule, Program program, List<InternshipApplication> internshipApplications) {
        super(id, nom, prenom, phone, email, password);
        this.matricule = matricule;
        this.program = program;
        this.cvList = null;
        this.internshipApplications = internshipApplications;
    }

    public Student(String nom, String prenom, String phone, String email, String password, String matricule, Program program, List<InternshipApplication> internshipApplications) {
        super(nom, prenom, phone, email, password);
        this.matricule = matricule;
        this.program = program;
        this.cvList = null;
        this.internshipApplications = internshipApplications;
    }

        public Student(String nom, String prenom, String phone, String email, String password, String matricule, Program program) {
        super(nom, prenom, phone, email, password);
        this.matricule = matricule;
        this.program = program;
        this.cvList = null;
        this.internshipApplications = new ArrayList<>();
    }

    @Override
    public UserDto toUserDto() {
        return new StudentDto(this);
    }
}
