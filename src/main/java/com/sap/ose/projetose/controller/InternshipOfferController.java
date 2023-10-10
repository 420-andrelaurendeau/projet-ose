package com.sap.ose.projetose.controller;


import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.service.InternshipOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interOfferJob")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipOfferController {
    private final InternshipOfferService internshipOfferService;

    @PostMapping("/save")
    public ResponseEntity<InternshipOfferDto> saveInterOffer(@RequestBody InternshipOfferDto internshipOfferDto) {

        System.out.println(internshipOfferDto.toString());
        InternshipOfferDto savedOfferJobDto = internshipOfferService.saveInternshipOfferJob(internshipOfferDto);

        return new ResponseEntity<>(savedOfferJobDto, HttpStatus.CREATED);
    }

    @GetMapping("/pendingOffers")
    public List<InternshipOfferDto> getPendingOffers() {
        return internshipOfferService.getInternOfferPending();
    }

    @GetMapping("/allOffers")
    public List<InternshipOfferDto> getAllOffers() {
        return internshipOfferService.getAllInternOffers();
    }

    @GetMapping("/OffersEtudiant")
    public List<InternshipOfferDto> getStudentOffers() {
        return internshipOfferService.getAcceptedInternshipOffer();
    }

    @GetMapping("/OffersEmp/{email}")
    public List<InternshipOfferDto> getInternOfferJob(@PathVariable String email){
        return internshipOfferService.getInternOfferByEmployeurEmail(email);
    }
}

