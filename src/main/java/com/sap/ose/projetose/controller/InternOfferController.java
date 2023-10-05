package com.sap.ose.projetose.controller;


import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.InternOffer;
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
    public ResponseEntity<InternOfferDto> saveInterOfferJob(@RequestBody InternOfferDto internOfferJobdto) {

        System.out.println(internOfferJobdto.toString());
        InternOfferDto savedOfferJobDto = offerJobService.saveInterOfferJob(internOfferJobdto);

        return new ResponseEntity<>(savedOfferJobDto, HttpStatus.CREATED);
    }

    @GetMapping("/pendingOffers")
    public List<InternOfferDto> getPendingOffers() {
        return offerJobService.getInternOfferPending();
    }

    @GetMapping("/allOffers")
    public List<InternOfferDto> getAllOffers() {
        return offerJobService.getAllInternOffers();
    }

    @GetMapping("/OffersEtudiant")
    public List<InternOfferDto> getOffersEtudiant() {
        return offerJobService.getInternOfferAccepted();
    }

    @GetMapping("/OffersEmp/{email}")
    public List<InternOfferDto> getInternOfferJob(@PathVariable String email){
        return offerJobService.getInternOfferByEmployeurEmail(email);
    }
}

