package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.TemplateContract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateContractDto {

    long id;
    String createdDate;
    Boolean isActive;
    long fileId;
    String fileName;
    String content;

    public TemplateContractDto(String createdDate, Boolean isActive, long fileId, String fileName, String content) {
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
        this.content = Base64.getEncoder().encodeToString(templateContract.getFile().getContent());
    }
}
