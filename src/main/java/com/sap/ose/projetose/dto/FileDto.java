package com.sap.ose.projetose.dto;


import com.sap.ose.projetose.modeles.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {

    private long id;
    private byte[] content;
    private String fileName;
    private boolean isAccepted;

    public FileDto(File file) {
        this.id = file.getId();
        this.content = file.getContent();
        this.fileName = file.getFileName();
        this.isAccepted = file.isAccepted();
    }

    public File fromDto() {
        File file = new File(content, fileName, isAccepted);
        return file;
    }
}
