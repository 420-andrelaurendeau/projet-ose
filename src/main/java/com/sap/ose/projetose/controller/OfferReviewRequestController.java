package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.service.OfferReviewRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
    @PreAuthorize("hasAuthority('internshipmanager')")
    public ResponseEntity<InternOfferDto> saveOfferReviewRequest(@RequestBody OfferReviewRequestDto offerReviewRequestDto){
        InternOfferDto internOfferDto = offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto);
        return new ResponseEntity<>(internOfferDto, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('internshipmanager')")
    public ResponseEntity<InternOfferDto> updateOfferReviewRequest(@RequestBody OfferReviewRequestDto offerReviewRequestDto){
        System.out.println("Update OfferReview");
        InternOfferDto internOfferDto = offerReviewRequestService.updateStateOfferReviewRequest(offerReviewRequestDto);
        return new ResponseEntity<>(internOfferDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OfferReviewRequestDto> getOfferReviewRequest(@PathVariable("id") Long id){
        OfferReviewRequestDto offerReviewRequestDto = offerReviewRequestService.getOfferReviewRequest(id);
        return new ResponseEntity<>(offerReviewRequestDto, HttpStatus.OK);
    }
}
