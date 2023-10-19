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

    private boolean isAccepted;

    @ManyToOne
    @JoinColumn
    private Utilisateur utilisateur;


    public File(byte[] content, String fileName, boolean isAccepted) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", content=" + Arrays.toString(content) +
                ", fileName='" + fileName + '\'' +
                ", isAccepted=" + isAccepted +
                ", etudiant=" + utilisateur +
                '}';
    }
}
