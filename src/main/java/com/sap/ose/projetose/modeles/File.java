package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

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

    private State isAccepted;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private InternshipCandidates internshipCandidates;


    public File(byte[] content, String fileName, State isAccepted) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
        defaultFile = false;
    }
    public File(byte[] content, String fileName, State isAccepted, Etudiant etudiant, InternshipCandidates internshipCandidates) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
        this.etudiant = etudiant;
        this.defaultFile = false;
        this.internshipCandidates = internshipCandidates;
    }
    public File(byte[] content, String fileName, State isAccepted, Etudiant etudiant,boolean defaultFile) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
        this.etudiant = etudiant;
        this.defaultFile = defaultFile;
    }

    public File(byte[] content, String fileName, Etudiant etudiant) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = State.PENDING;
        this.etudiant = etudiant;
        defaultFile = false;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", content=" + Arrays.toString(content) +
                ", fileName='" + fileName + '\'' +
                ", isAccepted=" + isAccepted +
                ", etudiant=" + etudiant +
                '}';
    }
}
