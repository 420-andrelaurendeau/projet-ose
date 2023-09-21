package com.sap.ose.projetose.model;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public final class Etudiant extends Utilisateur {
    String prenom;
    String matricule;
    String formation;
}
