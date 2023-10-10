package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.models.StudyProgram;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.models.User;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerDto extends UserDto {

    private String enterprise;
    private long studyProgramId;

    public EmployerDto(String lastName, String firstName, String phone, String email, String enterprise) {
        super(firstName, lastName, phone, email);
        this.enterprise = enterprise;
    }

    public EmployerDto(int id, String lastName, String firstName, String phone, String email, String enterprise) {
        super(id,lastName, firstName, phone, email);
        this.enterprise = enterprise;
    }

    public EmployerDto(Employer employer){
        super(employer.getFirstName(), employer.getLastName(), employer.getPhoneNumber(), employer.getEmail());
        this.enterprise = employer.getEnterprise();
        this.studyProgramId = employer.getStudyProgram().getId();
    }

    @Override
    public User toUser() {
        //FIXME: EmployerDto.toNewUser() should return a valid object.
        return new Student(getLastName(), getFirstName(), getPhoneNumber(), getEmail(), getEnterprise(), (StudyProgram) null,null,null);
    }
}
