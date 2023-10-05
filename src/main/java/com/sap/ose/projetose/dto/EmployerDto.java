package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.models.Program;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerDto extends UserDto {

    private String entreprise;
    private long programId;

    public EmployerDto(String nom, String prenom, String phone, String email, String entreprise) {
        super(nom, prenom, phone, email);
        this.entreprise = entreprise;
    }

    public EmployerDto(int id, String nom, String prenom, String phone, String email, String entreprise) {
        super(id,nom, prenom, phone, email);
        this.entreprise = entreprise;
    }

    public EmployerDto(Employer employer){
        super(employer.getLastName(), employer.getFirstName(), employer.getPhoneNumber(), employer.getEmail());
        this.entreprise = employer.getEntreprise();
        this.programId = employer.getProgram().getId();
    }

    @Override
    public User toNewUser() {
        return new Student(getLastName(), getFirstName(), getPhoneNumber(), getEmail(), getEntreprise(), (Program) null,null,null);
    }
}
