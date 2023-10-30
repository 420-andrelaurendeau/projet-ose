package com.sap.ose.projetose.modeles;

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
    public Internshipmanager reviewer;
    public Etats reviewState = Etats.PENDING;
    private String comment;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private InternOffer internOffer;
}
