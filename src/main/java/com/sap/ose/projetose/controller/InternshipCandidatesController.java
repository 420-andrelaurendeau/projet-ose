package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.service.InternshipCandidatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/intershipCandidates")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipCandidatesController {

    private final InternshipCandidatesService internshipCandidatesService;

    @PostMapping("/save")
    public ResponseEntity<InternshipCandidatesDto> saveIntershipCandidate(@RequestBody InternshipCandidatesDto internshipCandidatesDto) {
        InternshipCandidatesDto savedInternship = internshipCandidatesService.saveCandidates(internshipCandidatesDto);

        return new ResponseEntity<>(savedInternship,HttpStatus.CREATED);
    }

}
