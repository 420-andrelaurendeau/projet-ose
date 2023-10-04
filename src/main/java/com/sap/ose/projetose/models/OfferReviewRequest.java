package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferReviewRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String comment;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "internOffer_id")
    private InternshipOffer internhipOffer;

    @OneToOne
    @JoinColumn(name = "internshipmanager_id")
    public InternshipManager internshipManager;

    public OfferReviewRequest(InternshipOffer internhipOffer, String comment, InternshipManager internshipManager) {
        this.internhipOffer = internhipOffer;
        this.comment = comment;
        this.internshipManager = internshipManager;
    }
}
