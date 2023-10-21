package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.StudyProgramDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudyProgram extends BaseModel {
    private String nom;
    private String description;
}
