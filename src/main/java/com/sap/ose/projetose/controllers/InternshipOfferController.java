package com.sap.ose.projetose.controllers;


import com.sap.ose.projetose.dtos.InternshipOfferDto;
import com.sap.ose.projetose.services.InternshipOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/internshipOffer")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipOfferController {
    private final InternshipOfferService internshipOfferService;

    @PostMapping({"/update", "/new"})
    public ResponseEntity<InternshipOfferDto> createInternshipOffer(@RequestBody InternshipOfferDto internshipOfferDto) {

        System.out.println(internshipOfferDto.toString());
        InternshipOfferDto savedOfferJobDto = internshipOfferService.createOrUpdateInternshipOffer(internshipOfferDto);

        return new ResponseEntity<>(savedOfferJobDto, HttpStatus.CREATED);
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

