package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.InternshipManagerDto;
import com.sap.ose.projetose.dtos.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InternshipManager extends User {
    public InternshipManager(String lastName, String firstName, String phone, String email, String password, StudyProgram studyProgram) {
        super(lastName, firstName, phone, email, password, studyProgram);
    }

    public InternshipManagerDto toDto() {
        return new InternshipManagerDto(this);
    }
}
