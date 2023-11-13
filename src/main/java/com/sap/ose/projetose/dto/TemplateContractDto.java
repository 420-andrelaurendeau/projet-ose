package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.TemplateContract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateContractDto {

    long id;
    String createdDate;
    Boolean isActive;
    long fileId;
    String fileName;
    byte[] content;

    public TemplateContractDto(String createdDate, Boolean isActive, long fileId, String fileName, byte[] content) {
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.fileId = fileId;
        this.fileName = fileName;
        this.content = content;
    }

    public TemplateContractDto(TemplateContract templateContract) {
        this.createdDate = templateContract.getCreatedDate().toString();
        this.isActive = templateContract.getIsActive();
        this.fileId = templateContract.getFile().getId();
        this.fileName = templateContract.getFile().getFileName();
        this.content = templateContract.getFile().getContent();
    }
}
