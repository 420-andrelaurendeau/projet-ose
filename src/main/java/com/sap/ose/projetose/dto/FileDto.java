package com.sap.ose.projetose.dto;


import com.sap.ose.projetose.models.AssessmentState;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.User;
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
    private User uploader;
    private AssessmentState isAccepted;

    public FileDto(File file) {
        this.id = file.getId();
        this.content = file.getContent();
        this.fileName = file.getFileName();
        this.uploader = file.getUploader();
        this.isAccepted = file.getAsssessmentState();
    }

    public File fromDto() {
        return new File(fileName, uploader, content);
    }
}
