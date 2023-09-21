package com.sap.ose.projetose.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class FichierPDF {
    @Lob
    String DonneesBase64;
    String nom;

    @OneToOne
    Utilisateur proprietaire;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
