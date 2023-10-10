package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.Program;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyProgramDto {
    private long id;
    private String name;
    private String description;


    public StudyProgramDto(Program program) {
        this.id = program.getId();
        this.name = program.getNom();
        this.description = program.getDescription();
    }

    public Program toNewProgram() {
        return new Program(name, description);
    }
}
