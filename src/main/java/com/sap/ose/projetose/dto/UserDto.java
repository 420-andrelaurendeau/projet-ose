package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserDto {
    private long id;
    private String nom;
    private String prenom;
    private String phone;
    private String email;

    public UserDto(String nom, String prenom, String phone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
    }

    public abstract User toUser();
}
