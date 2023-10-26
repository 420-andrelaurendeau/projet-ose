package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.EmployerDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employer extends User {
    private String enterprise;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<InternshipOffer> internshipOffers;

    @ManyToMany(mappedBy = "id")
    private Set<StudyProgram> studyProgram;

    public Employer(String lastName, String firstName, String phone, String email, String password, String enterprise, Set<StudyProgram> studyProgram) {
        super(lastName, firstName, phone, email, password);
        this.enterprise = enterprise;
        this.studyProgram = studyProgram;
        this.internshipOffers = new ArrayList<>();
    }

    public EmployerDto toDto() {
        return new EmployerDto(this);
    }
}
