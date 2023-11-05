package com.sap.ose.projetose.modeles;

import com.sap.ose.projetose.repository.Contract;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Etudiant student;

    @ManyToOne
    @JoinColumn(name = "employeur_id")
    private Employeur employeur;

    @ManyToOne()
    @JoinColumn(name = "Internshipoffer_id")
    private InternOffer offer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private State stateStudent;
    private State stateEmployeur;


    public Stage(Etudiant student, InternOffer offer, State stateStudent, State stateEmployeur, Contract contract) {
        this.student = student;
        this.offer = offer;
        this.stateStudent = stateStudent;
        this.stateEmployeur = stateEmployeur;
        this.contract = contract;
    }
}
