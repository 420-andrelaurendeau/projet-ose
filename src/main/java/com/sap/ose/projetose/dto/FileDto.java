package com.sap.ose.projetose.dto;


import com.sap.ose.projetose.models.AssessmentState;
import com.sap.ose.projetose.models.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {

    private long id;
    private byte[] content;
    private String fileName;
    @NonNull
    private UserDto uploader;
    private AssessmentState isAccepted;

    public FileDto(File file) {
        this.id = file.getId();
        this.content = file.getContent();
        this.fileName = file.getFileName();
        this.uploader = file.getUploader().toUserDto();
        this.isAccepted = file.getAsssessmentState();
    }

    public File toFile() {
        return new File(fileName, uploader.toUser(), content);
    }
}
