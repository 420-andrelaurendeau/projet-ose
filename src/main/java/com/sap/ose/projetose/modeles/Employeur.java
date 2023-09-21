package com.sap.ose.projetose.modeles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("EMPLOYEUR")
public class Employeur extends Utilisateur {
    @Column(unique = true)
    private String nomEntreprise;
    private String programme;

    public Employeur(int id, String nom, String prenom, String email,String phone, String password, String nomEntreprise, String programme) {
        super(id, nom, prenom, email, phone, password);
        this.nomEntreprise = nomEntreprise;
        this.programme = programme;
    }

    public Employeur(String nom, String prenom, String email,String phone, String password, String nomEntreprise, String programme) {
        super(nom, prenom, email, phone, password);
        this.nomEntreprise = nomEntreprise;
        this.programme = programme;
    }
}