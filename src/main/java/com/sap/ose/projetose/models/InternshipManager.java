package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dto.InternshipManagerDto;
import com.sap.ose.projetose.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InternshipManager extends User {
    @OneToOne
    @JoinColumn(name = "programme_id")
    private StudyProgram studyProgram;

    public InternshipManager(String lastName, String firstName, String phone, String email, String password, StudyProgram studyProgram) {
        super(lastName, firstName, phone, email, password);
        this.studyProgram = studyProgram;
    }
    public InternshipManager(long id, String lastName, String firstName, String phone, String email, String password, StudyProgram studyProgram) {
        super(id, lastName, firstName, phone, email, password);
        this.studyProgram = studyProgram;
    }

    @Override
    public UserDto toUserDto() {
        return new InternshipManagerDto(this);
    }
}
