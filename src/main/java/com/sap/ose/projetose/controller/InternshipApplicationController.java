package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternshipApplicationDto;
import com.sap.ose.projetose.dto.newInternshipApplicationDto;
import com.sap.ose.projetose.service.InternshipApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internshipApplications")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipApplicationController {
    private final InternshipApplicationService internshipApplicationService;

    @PostMapping("/new")
    public ResponseEntity<InternshipApplicationDto> newOrUpdateInternshipApplication(@Valid @RequestBody newInternshipApplicationDto internshipApplicationDto) {
        InternshipApplicationDto savedInternshipApplicationDto = internshipApplicationService.createApplication(internshipApplicationDto);
        return new ResponseEntity<>(savedInternshipApplicationDto, HttpStatus.CREATED);
    }
}
