package com.sap.ose.projetose.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Internshipmanager extends Utilisateur{


    @OneToOne
    @JoinColumn(name = "programme_id")
    private Programme programme;

    public Internshipmanager(String nom, String prenom, String phone, String email, String password, Programme programme) {
        super(nom, prenom, phone, email, password);
        this.programme = programme;
    }
    public Internshipmanager(long id, String nom, String prenom, String phone, String email, String password, Programme programme) {
        super(id, nom, prenom, phone, email, password);
        this.programme = programme;
    }
}
