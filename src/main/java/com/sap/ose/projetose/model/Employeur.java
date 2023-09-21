package com.sap.ose.projetose.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Employeur extends Utilisateur {
    String raisonSociale;
    String adresse;
    String telephone;
    String siteWeb;
    String description;
}
