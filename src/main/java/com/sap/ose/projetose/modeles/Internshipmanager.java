package com.sap.ose.projetose.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Internshipmanager extends Utilisateur{
    public Internshipmanager(long id, String nom, String prenom, String phone, String email, String password, Programme programme) {
        super(id, nom, prenom, phone, email, password, programme);
    }

    public Internshipmanager(String nom, String prenom, String phone, String email, String password, Programme programme) {
        super(nom, prenom, phone, email, password);
        setProgramme(programme);
    }
}
