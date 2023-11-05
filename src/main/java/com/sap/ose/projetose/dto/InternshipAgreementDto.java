package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@Data
public class InternshipAgreementDto {

    long id;
    EmployeurDto employeur;
    EtudiantDto etudiantDto;
    InternOfferDto internOfferDto;
    State stateStudent;
    State stateEmployeur;
    long contractId;

    public InternshipAgreementDto(long id, EmployeurDto employeur, EtudiantDto etudiantDto, InternOfferDto internOfferDto, State stateStudent, State stateEmployeur, long contractId) {
        this.id = id;
        this.employeur = employeur;
        this.etudiantDto = etudiantDto;
        this.internOfferDto = internOfferDto;
        this.stateStudent = stateStudent;
        this.stateEmployeur = stateEmployeur;
        this.contractId = contractId;
    }



}
