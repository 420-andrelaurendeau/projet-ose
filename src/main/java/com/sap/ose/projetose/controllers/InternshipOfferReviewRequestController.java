package com.sap.ose.projetose.controllers;

import com.sap.ose.projetose.dtos.InternshipOfferDto;
import com.sap.ose.projetose.dtos.OfferReviewRequestDto;
import com.sap.ose.projetose.services.InternshipOfferReviewRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/offerReview")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipOfferReviewRequestController {
    private final InternshipOfferReviewRequestService internshipOfferReviewRequestService;

    @PostMapping("/update")
    public ResponseEntity<InternshipOfferDto> saveOfferReviewRequest(@RequestBody OfferReviewRequestDto offerReviewRequestDto) {
        InternshipOfferDto internshipOfferDto = internshipOfferReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto);
        return new ResponseEntity<>(internshipOfferDto, HttpStatus.CREATED);
    }
}
