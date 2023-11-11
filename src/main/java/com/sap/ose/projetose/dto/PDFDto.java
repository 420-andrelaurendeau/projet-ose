package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.PDF;
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

    public PDFDto(PDF pdf) {
        this.createdDate = pdf.getCreatedDate().toString();
        this.isActive = pdf.getIsActive();
        this.fileId = pdf.getFile().getId();
        this.fileName = pdf.getFile().getFileName();
        this.content = pdf.getFile().getContent();
    }
}
