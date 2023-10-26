package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.InternshipManagerDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InternshipManager extends User {
    @ManyToMany
    @JoinTable
    private Set<StudyProgram> studyProgram;

    public InternshipManager(String lastName, String firstName, String phone, String email, String password, Set<StudyProgram> studyProgram) {
        super(lastName, firstName, phone, email, password);
        this.studyProgram = studyProgram;
    }

    public InternshipManagerDto toDto() {
        return new InternshipManagerDto(this);
    }
}
