package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("EMPLOYEUR")
@Data
public class Employeur extends Utilisateur {

    @Column(unique = true)
    private String entreprise;

    @ManyToOne
    private Programme programme;

    @OneToMany(mappedBy = "employeur", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<InternOffer> internOffers;

    public Employeur(long id, String nom, String prenom, String email,String phone, String password, String entreprise, Programme programme) {
        super(id, nom, prenom, email, phone, password);
        this.entreprise = entreprise;
        this.programme = programme;
    }

    public Employeur(String nom, String prenom, String email,String phone, String password, String entreprise, Programme programme) {
        super(nom, prenom, email, phone, password);
        this.entreprise = entreprise;
        this.programme = programme;
    }



}
