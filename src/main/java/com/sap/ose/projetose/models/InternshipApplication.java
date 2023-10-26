package com.sap.ose.projetose.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratedColumn;

import java.util.List;

import static org.hibernate.annotations.GenerationTime.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"candidate_id", "internship_offer_id"}))
public class InternshipApplication extends BaseModel {
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Student candidate;

    @ManyToOne
    @JoinColumn
    private InternshipOffer internshipOffer;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn
    private List<File> files;
}
