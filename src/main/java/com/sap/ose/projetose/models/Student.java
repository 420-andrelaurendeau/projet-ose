package com.sap.ose.projetose.models;


import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ETUDIANT")
@Data
public class Student extends User {
    @Column(unique = true)
    private String matricule;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cv_id")
    private List<File> cv;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "etudiant_id")
    private List<InternshipApplication> internshipsCandidate;


    public Student(String nom, String prenom, String telephone, String email, String password, String matricule, Formation formation, List<InternshipApplication> internshipsCandidate) {
        super(nom, prenom, email, password, telephone, formation);
        this.matricule = matricule;
        this.internshipsCandidate = internshipsCandidate;
    }

    public Student(long id, String nom, String prenom, String telephone, String email, String password, String matricule, Formation formation, List<InternshipApplication> internshipsCandidate) {
        super(id, nom, prenom, email, password, telephone, formation);
        this.matricule = matricule;
        this.internshipsCandidate = internshipsCandidate;
    }

    public void AddCV(File cv) {
        this.cv.add(cv);
    }

    @Override
    public UserDto toUserDto() {
        return new StudentDto(
                getId(),
                getNom(),
                getPrenom(),
                getPhone(),
                getEmail(),
                getMatricule(),
                getFormation().getId(),
                getCv(),
                new ArrayList<>()
        );
    }

    public User fromUserDto(UserDto userDto) {
        return new Student(
                userDto.getId(),
                userDto.getNom(),
                userDto.getPrenom(),
                userDto.getPhone(),
                userDto.getEmail(),
                "",
                ((StudentDto) userDto).getMatricule(),
                getFormation(),
                getInternshipsCandidate()
        );
    }
}
