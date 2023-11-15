package com.sap.ose.projetose.modeles;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    public File file;

    public boolean signatureInternShipManager;

    public boolean signatureEmployer;

    public boolean signatureStudent;


    public Contract( Employeur employeur, Etudiant student, InternOffer internOffer, boolean signatureInternShipManager, boolean signatureEmployer, boolean signatureStudent, File file) {
        this.employeur = employeur;
        this.student = student;
        this.internOffer = internOffer;
        this.signatureInternShipManager = signatureInternShipManager;
        this.signatureEmployer = signatureEmployer;
        this.signatureStudent = signatureStudent;
        this.file = file;
    }

}
