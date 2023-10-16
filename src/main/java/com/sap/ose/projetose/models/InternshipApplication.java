package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dto.InternshipApplicationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InternshipApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Student candidate;

    @ManyToOne
    @JoinColumn
    private InternshipOffer internshipOffer;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<File> files;

    public InternshipApplication(Student candidate, InternshipOffer internshipOffer, List<File> files) {
        this.candidate = candidate;
        this.internshipOffer = internshipOffer;
        this.files = files;
    }

    public InternshipApplicationDto toDto() {
        return new InternshipApplicationDto(this);
    }
}
