package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.dto.NewInternshipApplicationDto;
import com.sap.ose.projetose.service.InternshipCandidatesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/internshipApplications")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipCandidatesController {
    private final InternshipCandidatesService internshipCandidatesService;

    @PostMapping("/new")
    public ResponseEntity<InternshipCandidatesDto> newInternshipApplication(@Valid @RequestBody NewInternshipApplicationDto internshipApplicationDto) {
        InternshipCandidatesDto newInternshipCandidatesDto = internshipCandidatesService.createApplication(internshipApplicationDto);
        return new ResponseEntity<>(newInternshipCandidatesDto, HttpStatus.CREATED);
    }
}
