package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.models.StudyProgram;
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


    public StudyProgramDto(StudyProgram studyProgram) {
        this.id = studyProgram.getId();
        this.name = studyProgram.getNom();
        this.description = studyProgram.getDescription();
    }
}
