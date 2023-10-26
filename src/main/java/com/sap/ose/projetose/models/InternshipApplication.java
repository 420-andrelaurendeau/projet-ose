package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InternshipApplication {
    @Id
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Student candidate;

    @Id
    @ManyToOne
    @JoinColumn
    private InternshipOffer internshipOffer;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<File> files;
}
