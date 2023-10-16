package com.sap.ose.projetose.dto;


import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.models.File;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Valid
public class FileTransferDto {
    private long id;
    @NotNull
    private byte[] content;
    @NotBlank
    private String fileName;
    private boolean isAccepted;
    @UserExists
    private long uploaderId;

    public FileTransferDto(File file) {
        this.id = file.getId();
        this.content = file.getContent();
        this.fileName = file.getFileName();
        this.isAccepted = file.isAccepted();
        this.uploaderId = file.getUser().getId();
    }
}
