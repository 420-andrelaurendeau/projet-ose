package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    @ToString.Exclude
    private InternshipCandidates internshipCandidates;


    public File(byte[] content, String fileName, State isAccepted) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
    }
    public File(byte[] content, String fileName, State isAccepted, Etudiant etudiant, InternshipCandidates internshipCandidates) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
        this.etudiant = etudiant;
        this.internshipCandidates = internshipCandidates;
    }
    public File(byte[] content, String fileName, State isAccepted, Etudiant etudiant) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
        this.etudiant = etudiant;
    }

    public File(byte[] content, String fileName, Etudiant etudiant) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = State.PENDING;
        this.etudiant = etudiant;
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
