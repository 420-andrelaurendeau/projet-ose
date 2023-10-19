package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Programme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammeDto {
    private long id;
    private String nom;
    private String description;


    public ProgrammeDto(Programme programme) {
        this.id = programme.getId();
        this.nom = programme.getNom();
        this.description = programme.getDescription();
    }


    public Programme fromDto() {
        return new Programme(nom, description);
    }
}
