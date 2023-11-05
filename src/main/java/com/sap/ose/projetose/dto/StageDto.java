package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Stage;
import com.sap.ose.projetose.modeles.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StageDto {
    private long id;
    private long student_id;
    private long offer_id;
    private State stateStudent;
    private State stateEmployeur;
    private long contract_id;


    public StageDto(Stage stage){
        this.id = stage.getId();
        this.student_id = stage.getStudent().getId();
        this.offer_id = stage.getOffer().getId();
        this.stateStudent = stage.getStateStudent();
        this.stateEmployeur = stage.getStateEmployeur();
        this.contract_id = stage.getContract().getId();
    }
}
