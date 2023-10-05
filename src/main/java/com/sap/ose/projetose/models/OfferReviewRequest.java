package com.sap.ose.projetose.models;

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
    @JoinColumn(name = "internOffer_id")
    private InternshipOffer internshipOffer;

    @ManyToOne
    @JoinColumn(name = "internshipmanager_id")
    public InternshipManager internshipManager;

    public OfferReviewRequest(InternshipOffer internshipOffer, String comment, InternshipManager internshipManager) {
        this.internshipOffer = internshipOffer;
        this.comment = comment;
        this.internshipManager = internshipManager;
    }
}
