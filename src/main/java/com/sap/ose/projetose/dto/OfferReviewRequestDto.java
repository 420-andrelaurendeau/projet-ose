package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.OfferReviewRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OfferReviewRequestDto {

    private long id;
    private String comment;
    private long internOfferId;
    private long internshipmanagerId;

    public OfferReviewRequestDto(OfferReviewRequest offerReviewRequest){
        this.id = offerReviewRequest.getId();
        this.comment = offerReviewRequest.getComment();
        this.internOfferId = offerReviewRequest.getInternOffer().getId();
        this.internshipmanagerId = offerReviewRequest.getInternshipmanager().getId();
    }

    public OfferReviewRequest fromDto(){
        return new OfferReviewRequest(null, comment, null);
    }
}
