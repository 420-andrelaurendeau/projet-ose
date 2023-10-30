package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.modeles.Etats;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OfferReviewRequestDto {
    private long id;
    private String comment;
    private Etats state;
    private long internshipOfferId;
    @UserExists
    private long internshipManagerId;

    public OfferReviewRequestDto(OfferReviewRequest offerReviewRequest) {
        this.id = offerReviewRequest.getId();
        this.comment = offerReviewRequest.getComment();
        this.internshipOfferId = offerReviewRequest.getInternOffer().getId();
        this.internshipManagerId = offerReviewRequest.getReviewer().getId();
    }
}
