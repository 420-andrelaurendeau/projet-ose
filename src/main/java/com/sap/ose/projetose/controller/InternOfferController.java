package com.sap.ose.projetose.controller;


import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.NewInternshipOfferDto;
import com.sap.ose.projetose.service.InternOfferService;
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
public class InternOfferController {
    private final InternOfferService internOfferService;

    @PostMapping("/new")
    public ResponseEntity<InternOfferDto> createInternshipOffer(@Valid @RequestBody NewInternshipOfferDto internshipOfferDto) {
        InternOfferDto newInternOfferDto = internOfferService.createInternshipOffer(internshipOfferDto);

        return new ResponseEntity<>(newInternOfferDto, HttpStatus.CREATED);
    }

    @GetMapping("/getPendingOffers")
    public List<InternOfferDto> getPendingOffers() {
        return internOfferService.getInternOfferPending();
    }

    @GetMapping("/getOffers")
    public List<InternOfferDto> getAllOffers() {
        return internOfferService.getAllInternOffers();
    }

    //FIXME: use a better address for this one, it isn't clear enough. Perhaps /getAcceptedOffers
    @GetMapping("/getStudentOffers")
    public List<InternOfferDto> getStudentOffers() {
        return internOfferService.getAcceptedInternshipOffer();
    }

    @GetMapping("/OffersEmp/{email}")
    public List<InternOfferDto> getInternOfferJob(@PathVariable String email) {
        return internOfferService.getInternOfferByEmployerEmail(email);
    }
}

