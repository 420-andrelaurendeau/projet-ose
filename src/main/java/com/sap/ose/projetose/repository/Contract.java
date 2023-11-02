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
    @JoinColumn(name = "stage_id")
    public Stage stage;

    @ManyToOne()
    @JoinColumn(name = "employeur_id")
    public Employeur employeur;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    public Etudiant student;

    @ManyToOne
    @JoinColumn(name = "internOffer_id")
    public InternOffer internOffer;

    @Lob
    public String signatureInternShipManager;
    @Lob
    public String signatureEmployer;
    @Lob
    public String signatureStudent;
    @Lob
    public String contract;

    public Contract(Stage stage, Employeur employeur, Etudiant student,InternOffer internOffer, String signatureInternShipManager, String signatureEmployer, String signatureStudent, String contract) {
        this.stage = stage;
        this.employeur = employeur;
        this.student = student;
        this.internOffer = internOffer;
        this.signatureInternShipManager = signatureInternShipManager;
        this.signatureEmployer = signatureEmployer;
        this.signatureStudent = signatureStudent;

        this.contract = contract;
    }

}
