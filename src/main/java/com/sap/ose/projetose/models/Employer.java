package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.EmployerDto;
import jakarta.persistence.*;
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
public class Employer extends User {
    private String enterprise;

    @ManyToOne
    private StudyProgram studyProgram;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<InternshipOffer> internshipOffers;

    public Employer(String lastName, String firstName, String phone, String email, String password, String enterprise, StudyProgram studyProgram) {
        super(lastName, firstName, phone, email, password, studyProgram);
        this.enterprise = enterprise;
        this.studyProgram = studyProgram;
        this.internshipOffers = new ArrayList<>();
    }

    public EmployerDto toDto() {
        return new EmployerDto(this);
    }
}
