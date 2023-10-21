package com.sap.ose.projetose.models;

import jakarta.persistence.Entity;
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
