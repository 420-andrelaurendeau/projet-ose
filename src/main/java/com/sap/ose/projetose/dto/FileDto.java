package com.sap.ose.projetose.dto;


import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.modeles.Etats;
import com.sap.ose.projetose.modeles.File;
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
public class FileDto {
    @NotNull(message = "{file.content.notBlank}")
    private byte[] content;
    @NotBlank(message = "{file.fileName.notBlank}")
    private String fileName;

    private Etats isAccepted;
    @UserExists
    private long uploaderId;

    public FileDto(File file) {
        this.content = file.getContent();
        this.fileName = file.getFileName();
        this.isAccepted = file.getEtats();
        this.uploaderId = file.getUtilisateur().getId();
    }
}
