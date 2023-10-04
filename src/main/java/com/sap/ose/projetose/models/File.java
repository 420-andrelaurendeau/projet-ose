package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    private AssessmentState asssessmentState = AssessmentState.PENDING;

    @OneToOne
    private User uploader;

    public File(@NonNull String fileName, User uploader, byte[] content) {
        this.content = content;
        this.fileName = fileName;
    }
}
