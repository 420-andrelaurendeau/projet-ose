package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Stage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    public long id;

    @ManyToOne()
    @JoinColumn(name = "employeur_id")
    public Employeur employeur;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    public Etudiant student;

    @ManyToOne
    @JoinColumn(name = "internOffer_id")
    public InternOffer internOffer;


    public boolean signatureInternShipManager;

    public boolean signatureEmployer;

    public boolean signatureStudent;
    @Lob
    public String contract;

    public Contract( Employeur employeur, Etudiant student, InternOffer internOffer, boolean signatureInternShipManager, boolean signatureEmployer, boolean signatureStudent, String contract) {
        this.employeur = employeur;
        this.student = student;
        this.internOffer = internOffer;
        this.signatureInternShipManager = signatureInternShipManager;
        this.signatureEmployer = signatureEmployer;
        this.signatureStudent = signatureStudent;

        this.contract = contract;
    }

}