package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.service.OfferReviewRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/offerReview")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class OfferReviewRequestController {
    private final OfferReviewRequestService offerReviewRequestService;
    //FIXME: Add a specific method to update state. Review Requests are created by the InternOffer Controller on creation of an offer.
}
