package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.OfferReviewRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OfferReviewRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String comment;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private InternshipOffer internshipOffer;

    @ManyToOne
    @JoinColumn
    public InternshipManager internshipManager;

    public ApprovalStatus reviewState = ApprovalStatus.PENDING;

    public OfferReviewRequestDto toDto() {
        return new OfferReviewRequestDto(this);
    }
}
