package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.service.InternshipOfferReviewRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offerReview")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipOfferReviewRequestController {
    private final InternshipOfferReviewRequestService internshipOfferReviewRequestService;

    @PostMapping("/update")
    public ResponseEntity<InternshipOfferDto> saveOfferReviewRequest(@RequestBody OfferReviewRequestDto offerReviewRequestDto){
        InternshipOfferDto internshipOfferDto = internshipOfferReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto);
        return new ResponseEntity<>(internshipOfferDto, HttpStatus.CREATED);
    }
}
