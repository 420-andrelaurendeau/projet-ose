package com.sap.ose.projetose.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PDFDto {

    long id;
    String createdDate;
    Boolean isActive;
    long fileId;
    String fileName;
    byte[] content;

    public PDFDto(String createdDate, Boolean isActive, long fileId, String fileName, byte[] content) {
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.fileId = fileId;
        this.fileName = fileName;
        this.content = content;
    }
}
