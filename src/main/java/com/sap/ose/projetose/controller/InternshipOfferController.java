package com.sap.ose.projetose.controller;


import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.service.InternshipOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interOfferJob")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipOfferController {

    private final InternshipOfferService offerJobService;

    @PostMapping("/save")
    public ResponseEntity<InternshipOfferDto> saveInterOfferJob(@RequestBody InternshipOfferDto internOfferJobdto) {

        InternshipOfferDto savedOfferJobDto = offerJobService.saveInterOfferJob(internOfferJobdto);

        return new ResponseEntity<>(savedOfferJobDto, HttpStatus.CREATED);
    }

    @GetMapping("/pendingOffers")
    public List<InternshipOfferDto> getPendingOffers() {
        return offerJobService.getInternOfferPending();
    }

    @GetMapping("/allOffers")
    public List<InternshipOfferDto> getAllOffers() {
        return offerJobService.getInternOffers();
    }
}

