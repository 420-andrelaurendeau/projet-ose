package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.PDFDto;
import com.sap.ose.projetose.service.PDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PDFController {

    PDFService pdfService;

    @Autowired
    public PDFController(PDFService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('internshipmanager')")
    public ResponseEntity<PDFDto> savePDF(PDFDto pdfDto) {
        PDFDto savedPDF = pdfService.save(pdfDto);
        return ResponseEntity.ok(savedPDF);
    }

    @GetMapping("/getCurrentPDF")
    @PreAuthorize("hasAuthority('internshipmanager')")
    public ResponseEntity<PDFDto> getCurrentPDF() {
        PDFDto currentPDF = pdfService.getCurrentPDF();
        return ResponseEntity.ok(currentPDF);
    }

}
