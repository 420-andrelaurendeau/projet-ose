package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.StudyProgramDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String description;

    public StudyProgram(String name, String description) {
        this.nom = name;
        this.description = description;
    }

    public StudyProgramDto toDto() {
        return new StudyProgramDto(this);
    }
}
