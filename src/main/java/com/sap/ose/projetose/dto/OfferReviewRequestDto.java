package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.OfferReviewRequest;
import com.sap.ose.projetose.models.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OfferReviewRequestDto {

    private long id;
    private String comment;
    private ApprovalStatus state;
    private long internshipOfferId;
    private long internshipManagerId;

    public OfferReviewRequestDto(OfferReviewRequest offerReviewRequest){
        this.id = offerReviewRequest.getId();
        this.comment = offerReviewRequest.getComment();
        this.internshipOfferId = offerReviewRequest.getInternshipOffer().getId();
        this.internshipManagerId = offerReviewRequest.getInternshipManager().getId();
    }

    public OfferReviewRequest fromDto(){
        return new OfferReviewRequest(null, comment, null);
    }
}
