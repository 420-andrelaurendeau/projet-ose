package com.sap.ose.projetose.modeles;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Programme extends BaseModel {
    private String nom;
    private String description;
}
