package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InternshipApplication extends BaseModel {
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Student candidate;

    @ManyToOne
    @JoinColumn
    private InternshipOffer internshipOffer;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<File> files;
}
