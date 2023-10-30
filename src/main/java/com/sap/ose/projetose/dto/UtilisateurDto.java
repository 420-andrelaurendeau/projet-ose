package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Utilisateur;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class UtilisateurDto {
    private long id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;

    public UtilisateurDto(Utilisateur utilisateur) {
        setId(utilisateur.getId());
        setLastName(utilisateur.getLastName());
        setFirstName(utilisateur.getFirstName());
        setPhoneNumber(utilisateur.getPhoneNumber());
        setEmail(utilisateur.getEmail());
    }
}
