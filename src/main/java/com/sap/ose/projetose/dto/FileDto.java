package com.sap.ose.projetose.dto;


import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {

    private long id;
    private byte[] content;
    private String fileName;
    private boolean isAccepted;
    private long uploaderId;

    public FileDto(File file) {
        this.id = file.getId();
        this.content = file.getContent();
        this.fileName = file.getFileName();
        this.isAccepted = file.isAccepted();
        this.uploaderId = Optional.ofNullable(file.getUtilisateur()).map(Utilisateur::getId).orElse(0L);
    }

    public File fromDto() {
        return new File(content, fileName, isAccepted);
    }
}
