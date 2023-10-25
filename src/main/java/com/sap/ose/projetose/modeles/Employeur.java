package com.sap.ose.projetose.modeles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("EMPLOYEUR")
public class Employeur extends Utilisateur {
    @Column(unique = true)
    private String entreprise;

    @ManyToOne
    private Programme programme;

    @OneToMany(mappedBy = "employeur", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<InternOffer> internOffers;

    public Employeur(long id, String nom, String prenom, String phone, String email, String password, String entreprise, Programme programme) {
        super(id, nom, prenom, phone, Role.EMPLOYEUR, phone, password);
        this.entreprise = entreprise;
        this.programme = programme;
    }

    public Employeur(String nom, String prenom, String email,String phone, String password, String entreprise, Programme programme) {
        super(nom, prenom, phone, Role.EMPLOYEUR,email, password);
        this.entreprise = entreprise;
        this.programme = programme;
        this.internOffers = new ArrayList<>();
    }

}
