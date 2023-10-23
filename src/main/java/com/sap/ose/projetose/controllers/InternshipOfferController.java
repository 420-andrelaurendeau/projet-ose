package com.sap.ose.projetose.controllers;


import com.sap.ose.projetose.dtos.InternshipOfferDto;
import com.sap.ose.projetose.dtos.NewInternshipOfferDto;
import com.sap.ose.projetose.services.InternshipOfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@Validated
@RestController
@RequestMapping("/api/internshipOffer")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipOfferController {
    private final InternshipOfferService internshipOfferService;

    @PostMapping("/new")
    public ResponseEntity<InternshipOfferDto> createInternshipOffer(@Valid @RequestBody NewInternshipOfferDto internshipOfferDto) {
        InternshipOfferDto newInternshipOfferDto = internshipOfferService.createInternshipOffer(internshipOfferDto);

        return new ResponseEntity<>(newInternshipOfferDto, HttpStatus.CREATED);
    }

    @GetMapping("/getPendingOffers")
    public List<InternshipOfferDto> getPendingOffers() {
        return internshipOfferService.getInternOfferPending();
    }

    @GetMapping("/getOffers")
    public List<InternshipOfferDto> getAllOffers() {
        return internshipOfferService.getAllInternOffers();
    }

    @GetMapping("/getStudentOffers")
    public List<InternshipOfferDto> getStudentOffers() {
        return internshipOfferService.getAcceptedInternshipOffer();
    }

    @GetMapping("/OffersEmp/{email}")
    public List<InternshipOfferDto> getInternOfferJob(@PathVariable String email) {
        return internshipOfferService.getInternOfferByEmployerEmail(email);
    }
}

