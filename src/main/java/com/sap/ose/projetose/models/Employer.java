package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("EMPLOYEUR")
@Data
public class Employer extends User {

    @Column(unique = true)
    private String entreprise;


    public Employer(long id, String nom, String prenom, String email, String phone, String password, String entreprise, Formation formation) {
        super(id, nom, prenom, phone, password, email, formation);
        this.entreprise = entreprise;
    }

    public Employer(String nom, String prenom, String email, String phone, String password, String entreprise, Formation formation) {
        super(nom, prenom, phone, password, email, formation);
        this.entreprise = entreprise;
    }


    @Override
    public UserDto toUserDto() {
        return null;
    }
}
