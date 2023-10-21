package com.sap.ose.projetose.models;


import com.sap.ose.projetose.dtos.StudentDto;
import com.sap.ose.projetose.dtos.UserDto;
import jakarta.persistence.*;
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

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<File> cvList;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<InternshipApplication> internshipApplications;
}
