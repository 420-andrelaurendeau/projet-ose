package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternshipApplicationDto;
import com.sap.ose.projetose.service.InternshipApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/intershipCandidates")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipApplicationController {
    private final InternshipApplicationService internshipApplicationService;

    @PostMapping("/saveCandidats")
    public ResponseEntity<InternshipApplicationDto> saveIntershipApplication(@RequestBody InternshipApplicationDto internshipApplicationDto) {
        InternshipApplicationDto savedInternshipApplicationDto = internshipApplicationService.saveApplication(internshipApplicationDto);
        return new ResponseEntity<>(savedInternshipApplicationDto,HttpStatus.CREATED);
    }

}
