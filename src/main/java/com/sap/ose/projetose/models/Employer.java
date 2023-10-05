package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dto.EmployerDto;
import com.sap.ose.projetose.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("EMPLOYEUR")
public class Employer extends User {
    @Column(unique = true)
    private String entreprise;

    @ManyToOne
    private Program program;

    @OneToMany(mappedBy = "employeur", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<InternshipOffer> internshipOffers;

    public Employer(long id, String nom, String prenom, String email, String phone, String password, String entreprise, Program program) {
        super(id, nom, prenom, email, phone, password);
        this.entreprise = entreprise;
        this.program = program;
    }

    public Employer(String nom, String prenom, String email, String phone, String password, String entreprise, Program program) {
        super(nom, prenom, phone, email, password);
        this.entreprise = entreprise;
        this.program = program;
        this.internshipOffers = new ArrayList<>();
    }

    public Employer(String nom, String prenom, String telephone, String email, String password, String entreprise) {
        super(nom, prenom, telephone, email, password);
        this.entreprise = entreprise;
    }

    @Override
    public UserDto toUserDto() {
        return new EmployerDto(this);
    }
}
