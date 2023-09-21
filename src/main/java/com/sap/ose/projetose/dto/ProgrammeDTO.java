package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Programme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammeDTO {
    private int id;
    private String nom;
    private String description;


    public ProgrammeDTO(Programme programme) {
        this.id = programme.getId();
        this.nom = programme.getNom();
        this.description = programme.getDescription();
    }


    public Programme fromDto() {
        return new Programme(id, nom, description);
    }
}