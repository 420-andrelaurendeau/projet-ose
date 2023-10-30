package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class File extends BaseModel {
    @Lob
    private byte[] content;
    private String fileName;
    private Etats etats;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Utilisateur utilisateur;
}
