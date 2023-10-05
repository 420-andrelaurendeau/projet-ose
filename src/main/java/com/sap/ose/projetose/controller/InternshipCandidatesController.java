package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternshipApplicationDto;
import com.sap.ose.projetose.service.InternshipCandidatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/intershipCandidates")
@CrossOrigin(origins = "http://localhost:3000")
public class InternshipCandidatesController {

    InternshipCandidatesService internshipCandidatesService;

    public InternshipCandidatesController(InternshipCandidatesService internshipCandidatesService) {
        this.internshipCandidatesService = internshipCandidatesService;
    }
    @PostMapping("/saveCandidats")
    public ResponseEntity<InternshipApplicationDto> saveIntershipCandidate(@RequestBody InternshipApplicationDto internshipApplicationDto) {
        InternshipApplicationDto savedInternship = internshipCandidatesService.saveCandidates(internshipApplicationDto);
        return new ResponseEntity<>(savedInternship,HttpStatus.CREATED);
    }

}
