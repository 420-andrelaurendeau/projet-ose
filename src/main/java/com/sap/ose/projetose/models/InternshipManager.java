package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.InternshipManagerDto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InternshipManager extends User {
    public InternshipManager(String lastName, String firstName, String phone, String email, String password, StudyProgram studyProgram) {
        super(lastName, firstName, phone, email, password, studyProgram);
    }

    public InternshipManagerDto toDto() {
        return new InternshipManagerDto(this);
    }
}
