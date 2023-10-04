package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.AssessmentState;
import com.sap.ose.projetose.models.OfferReviewRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OfferReviewRequestDto {

    private long id;
    private String comment;
    private AssessmentState state;
    private long internOfferId;
    private long internshipmanagerId;

    public OfferReviewRequestDto(OfferReviewRequest offerReviewRequest){
        this.id = offerReviewRequest.getId();
        this.comment = offerReviewRequest.getComment();
        this.internOfferId = offerReviewRequest.getInternhipOffer().getId();
        this.internshipmanagerId = offerReviewRequest.getInternshipManager().getId();
    }

    public OfferReviewRequest fromDto(){
        return new OfferReviewRequest(null, comment, null);
    }
}
