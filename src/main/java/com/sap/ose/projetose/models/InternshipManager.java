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
    private Program program;

    public InternshipManager(String nom, String prenom, String phone, String email, String password, Program program) {
        super(nom, prenom, phone, email, password);
        this.program = program;
    }
    public InternshipManager(long id, String nom, String prenom, String phone, String email, String password, Program program) {
        super(id, nom, prenom, phone, email, password);
        this.program = program;
    }

    @Override
    public UserDto toUserDto() {
        return new InternshipManagerDto(this);
    }
}
