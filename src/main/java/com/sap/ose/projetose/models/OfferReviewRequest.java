package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.OfferReviewRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OfferReviewRequest extends BaseModel {
    private String comment;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private InternshipOffer internshipOffer;
    @ManyToOne
    @JoinColumn
    public InternshipManager internshipManager;
    public ApprovalStatus reviewState = ApprovalStatus.PENDING;
}
