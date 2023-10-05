package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.service.OfferReviewRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offerReviewRequest")
@CrossOrigin(origins = "http://localhost:3000")
public class OfferReviewRequestController {


    private final OfferReviewRequestService offerReviewRequestService;

    @Autowired
    public OfferReviewRequestController(OfferReviewRequestService offerReviewRequestService) {
        this.offerReviewRequestService = offerReviewRequestService;
    }


    @PostMapping("/save")
    public ResponseEntity<InternshipOfferDto> saveOfferReviewRequest(@RequestBody OfferReviewRequestDto offerReviewRequestDto){
        InternshipOfferDto internshipOfferDto = offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto);
        return new ResponseEntity<>(internshipOfferDto, HttpStatus.CREATED);
    }
}
