package com.sap.ose.projetose.modeles;

import com.sap.ose.projetose.dto.UtilisateurDto;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Utilisateur extends BaseModel {
    private String lastName;
    private String firstName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @ToString.Exclude
    private String password;

    public abstract UtilisateurDto toDto();
}