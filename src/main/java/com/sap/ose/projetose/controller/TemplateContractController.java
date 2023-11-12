package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.TemplateContractDto;
import com.sap.ose.projetose.service.TemplateContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TemplateContractController {

    TemplateContractService templateContractService;

    @Autowired
    public TemplateContractController(TemplateContractService templateContractService) {
        this.templateContractService = templateContractService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('internshipmanager')")
    public ResponseEntity<TemplateContractDto> savePDF(TemplateContractDto templateContractDto) {
        TemplateContractDto savedPDF = templateContractService.save(templateContractDto);
        return ResponseEntity.ok(savedPDF);
    }

    @GetMapping("/getCurrentPDF")
    @PreAuthorize("hasAuthority('internshipmanager')")
    public ResponseEntity<TemplateContractDto> getCurrentPDF() {
        TemplateContractDto currentPDF = templateContractService.getCurrentPDF();
        return ResponseEntity.ok(currentPDF);
    }

}
