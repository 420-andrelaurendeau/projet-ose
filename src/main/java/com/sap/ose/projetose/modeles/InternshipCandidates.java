package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"candidate_id", "internship_offer_id"}))
public class InternshipCandidates extends BaseModel {
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Etudiant candidate;

    @ManyToOne
    @JoinColumn
    private InternOffer internOffer;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn
    private List<File> files;
}
