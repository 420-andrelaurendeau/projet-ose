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

    public Employer(long id, String lastName, String firstName, String email, String phone, String password, String enterprise, StudyProgram studyProgram) {
        super(id, lastName, firstName, email, phone, password);
        this.enterprise = enterprise;
        this.studyProgram = studyProgram;
    }

    public Employer(String lastName, String firstName, String email, String phone, String password, String enterprise, StudyProgram studyProgram) {
        super(lastName, firstName, phone, email, password);
        this.enterprise = enterprise;
        this.studyProgram = studyProgram;
        this.internshipOffers = new ArrayList<>();
    }

    public Employer(String lastName, String firstName, String telephone, String email, String password, String enterprise) {
        super(lastName, firstName, telephone, email, password);
        this.enterprise = enterprise;
    }

    @Override
    public UserDto toUserDto() {
        return new EmployerDto(this);
    }
}
