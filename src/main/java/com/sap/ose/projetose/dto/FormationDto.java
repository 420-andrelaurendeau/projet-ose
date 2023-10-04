package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.Formation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormationDto {
    private long id;
    private String nom;
    private String description;


    public FormationDto(Formation formation) {
        this.id = formation.getId();
        this.nom = formation.getNom();
        this.description = formation.getDescription();
    }


    public Formation fromDto() {
        return new Formation(id, nom, description);
    }
}
