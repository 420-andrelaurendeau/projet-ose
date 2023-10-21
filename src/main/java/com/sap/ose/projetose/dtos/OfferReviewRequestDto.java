package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.models.OfferReviewRequest;
import com.sap.ose.projetose.models.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OfferReviewRequestDto {

    private long id;
    private String comment;
    private ApprovalStatus state;
    private long internshipOfferId;
    @UserExists
    private long internshipManagerId;

    public OfferReviewRequestDto(OfferReviewRequest offerReviewRequest){
        this.id = offerReviewRequest.getId();
        this.comment = offerReviewRequest.getComment();
        this.internshipOfferId = offerReviewRequest.getInternshipOffer().getId();
        this.internshipManagerId = offerReviewRequest.getInternshipManager().getId();
    }
}
