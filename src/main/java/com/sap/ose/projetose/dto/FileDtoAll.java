package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDtoAll {
    private long id;
    private byte[] content;
    private String fileName;
    private State isAccepted;
    private EtudiantDto etudiant;

    public FileDtoAll(File file) {
        this.id = file.getId();
        this.content = file.getContent();
        this.fileName = file.getFileName();
        this.isAccepted = file.getIsAccepted();
        this.etudiant = new EtudiantDto(file.getEtudiant());
    }

    public File fromDto() {
        return new File(content, fileName, isAccepted, etudiant.fromDto());
    }
}
