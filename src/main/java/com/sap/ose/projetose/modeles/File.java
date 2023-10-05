package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] content;

    private String fileName;

    private boolean isAccepted;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private InternshipCandidates internshipCandidates;


    public File(byte[] content, String fileName, boolean isAccepted) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
    }
}
