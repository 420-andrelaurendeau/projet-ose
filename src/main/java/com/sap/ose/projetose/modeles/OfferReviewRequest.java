package com.sap.ose.projetose.modeles;

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
    @JoinColumn
    private InternOffer internOffer;

    @ManyToOne
    @JoinColumn
    public Internshipmanager internshipmanager;

    public OfferReviewRequest(InternOffer internOffer, String comment, Internshipmanager internshipmanager) {
        this.internOffer = internOffer;
        this.comment = comment;
        this.internshipmanager = internshipmanager;
    }
}
