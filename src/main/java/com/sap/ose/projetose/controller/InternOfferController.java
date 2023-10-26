package com.sap.ose.projetose.controller;


import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.service.InternOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('EMPLOYEUR')")
    public ResponseEntity<InternOfferDto> saveInterOfferJob(@RequestBody InternOfferDto internOfferJobdto) {
        InternOfferDto savedOfferJobDto = offerJobService.saveInterOfferJob(internOfferJobdto);

        return new ResponseEntity<>(savedOfferJobDto, HttpStatus.CREATED);
    }

    @GetMapping("/pendingOffers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<InternOfferDto> getPendingOffers() {
        return offerJobService.getInternOfferPending();
    }

    @GetMapping("/allOffers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<InternOfferDto> getAllOffers() {
        return offerJobService.getAllInternOffers();
    }

    @GetMapping("/OffersEtudiant")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('EMPLOYEUR') OR hasAuthority('STUDENT')")
    public List<InternOfferDto> getOffersEtudiant() {
        return offerJobService.getInternOfferAccepted();
    }

    @GetMapping("/OffersEmp/{email}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('EMPLOYEUR')")
    public List<InternOfferDto> getInternOfferJob(@PathVariable String email){
        return offerJobService.getInternOfferByEmployeurEmail(email);
    }
}

