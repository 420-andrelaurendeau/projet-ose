package com.sap.ose.projetose.controller;


import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.service.InternOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interOfferJob")
@CrossOrigin(origins = "http://localhost:3000")
public class InternOfferController {

    private final InternOfferService offerJobService;

    @Autowired
    public InternOfferController(InternOfferService offerJobService) {
        this.offerJobService = offerJobService;
    }

    @PostMapping("/save")
    public ResponseEntity<InternshipOfferDto> saveInterOfferJob(@RequestBody InternshipOfferDto internOfferJobdto) {

        System.out.println(internOfferJobdto.toString());
        InternshipOfferDto savedOfferJobDto = offerJobService.saveInterOfferJob(internOfferJobdto);

        return new ResponseEntity<>(savedOfferJobDto, HttpStatus.CREATED);
    }

    @GetMapping("/pendingOffers")
    public List<InternshipOfferDto> getPendingOffers() {
        return offerJobService.getInternOfferPending();
    }

    @GetMapping("/allOffers")
    public List<InternshipOfferDto> getAllOffers() {
        return offerJobService.getAllInternOffers();
    }

    @GetMapping("/OffersEtudiant")
    public List<InternshipOfferDto> getOffersEtudiant() {
        return offerJobService.getInternOfferAccepted();
    }

    @GetMapping("/OffersEmp/{email}")
    public List<InternshipOfferDto> getInternOfferJob(@PathVariable String email){
        return offerJobService.getInternOfferByEmployeurEmail(email);
    }
}

