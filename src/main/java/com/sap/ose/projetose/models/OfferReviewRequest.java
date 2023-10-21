package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OfferReviewRequest extends BaseModel {
    @ManyToOne
    @JoinColumn
    public InternshipManager internshipManager;
    public ApprovalStatus reviewState = ApprovalStatus.PENDING;
    private String comment;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private InternshipOffer internshipOffer;
}
