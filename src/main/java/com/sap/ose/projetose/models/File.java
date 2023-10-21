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
    @JoinColumn
    @ToString.Exclude
    private User user;


    public File(byte[] content, String fileName, boolean isAccepted, User user) {
        this.content = content;
        this.fileName = fileName;
        this.isAccepted = isAccepted;
        this.user = user;
    }
}
