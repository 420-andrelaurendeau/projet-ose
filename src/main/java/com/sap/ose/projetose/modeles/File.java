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

    public File(byte[] content, String fileName, boolean isAccepted) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
    }
}
