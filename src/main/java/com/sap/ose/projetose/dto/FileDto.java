package com.sap.ose.projetose.dto;


import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.State;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {

    private long id;
    @Lob
    private byte[] content;
    private String fileName;
    private State isAccepted;

    public FileDto(File file) {
        this.id = file.getId();
        this.content = file.getContent();
        this.fileName = file.getFileName();
        this.isAccepted = file.getIsAccepted();
    }

    public File fromDto() {
        return new File(content, fileName, isAccepted);
    }
}