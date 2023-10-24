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
    //FIXME: Add a specific method to update state. Review Requests are created by the InternshipOffer Controller on creation of an offer.
}
