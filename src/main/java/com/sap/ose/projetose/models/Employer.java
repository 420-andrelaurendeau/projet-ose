package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.EmployerDto;
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
public class Employer extends User {
    private String enterprise;

    @ManyToOne
    private StudyProgram studyProgram;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<InternshipOffer> internshipOffers;

    public Employer(String lastName, String firstName, String email, String phone, String password, String enterprise, StudyProgram studyProgram) {
        super(lastName, firstName, email, phone, password, studyProgram);
        this.enterprise = enterprise;
        this.studyProgram = studyProgram;
    }

    public EmployerDto toDto() {
        return new EmployerDto(this);
    }
}
