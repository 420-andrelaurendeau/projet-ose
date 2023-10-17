package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.service.InternshipCandidatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intershipCandidates")
@CrossOrigin(origins = "http://localhost:3000")
public class InternshipCandidatesController {

    InternshipCandidatesService internshipCandidatesService;

    public InternshipCandidatesController(InternshipCandidatesService internshipCandidatesService) {
        this.internshipCandidatesService = internshipCandidatesService;
    }
    @PostMapping("/saveCandidats")
    public ResponseEntity<InternshipCandidatesDto> saveIntershipCandidate(@RequestBody InternshipCandidatesDto internshipCandidatesDto) {
        InternshipCandidatesDto savedInternship = internshipCandidatesService.saveCandidates(internshipCandidatesDto);
        return new ResponseEntity<>(savedInternship,HttpStatus.CREATED);
    }

    @GetMapping("/getCandidats")
    public ResponseEntity<List<InternshipCandidatesDto>> getIntershipCandidate() {
        List<InternshipCandidatesDto> savedInternship = internshipCandidatesService.getCandidates();
        return new ResponseEntity<>(savedInternship,HttpStatus.FOUND);
    }

    @PostMapping("/acceptCandidats")
    public ResponseEntity<InternshipCandidatesDto> acceptIntershipCandidate(@RequestBody Long internshipCandidatesId) {
        InternshipCandidatesDto savedInternship = internshipCandidatesService.acceptCandidates(internshipCandidatesId);
        return new ResponseEntity<>(savedInternship,HttpStatus.CREATED);
    }

    @PostMapping("/declineCandidats")
    public ResponseEntity<InternshipCandidatesDto> declineIntershipCandidate(@RequestBody Long internshipCandidatesId) {
        InternshipCandidatesDto savedInternship = internshipCandidatesService.declineCandidates(internshipCandidatesId);
        return new ResponseEntity<>(savedInternship,HttpStatus.CREATED);
    }

    @GetMapping("/getPendingCandidates")
    public ResponseEntity<List<InternshipCandidatesDto>> getPendingCandidates() {
        List<InternshipCandidatesDto> savedInternship = internshipCandidatesService.getPendingCandidates();
        return new ResponseEntity<>(savedInternship,HttpStatus.FOUND);
    }

    @GetMapping("/getAcceptedCandidates")
    public ResponseEntity<List<InternshipCandidatesDto>> getAcceptedCandidates() {
        List<InternshipCandidatesDto> savedInternship = internshipCandidatesService.getAcceptedCandidates();
        return new ResponseEntity<>(savedInternship,HttpStatus.FOUND);
    }

    @GetMapping("/getDeclinedCandidates")
    public ResponseEntity<List<InternshipCandidatesDto>> getDeclinedCandidates() {
        List<InternshipCandidatesDto> savedInternship = internshipCandidatesService.getDeclinedCandidates();
        return new ResponseEntity<>(savedInternship,HttpStatus.FOUND);
    }

}
