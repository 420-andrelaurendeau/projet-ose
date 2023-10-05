package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dto.UserDto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
public class InternshipManager extends User {
    public InternshipManager(String nom, String prenom, String phone, String email, String password, Formation formation) {
        super(nom, prenom, email, password, phone, formation);
    }

    @Override
    public UserDto toUserDto() {
        return null;
    }
}
