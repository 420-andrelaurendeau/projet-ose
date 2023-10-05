package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
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
    @ToString.Exclude
    private Student student;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    @ToString.Exclude
    private InternshipApplication internshipApplication;


    public File(byte[] content, String fileName, boolean isAccepted) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
    }
}
