package com.sap.ose.projetose.modeles;

import com.sap.ose.projetose.dto.EmployeurDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employeur extends Utilisateur {
    private String enterprise;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<InternOffer> internOffers;

    @ManyToMany
    private Set<Programme> programme;

    public Employeur(String lastName, String firstName, String phone, String email, String password, String enterprise, Set<Programme> programme) {
        super(lastName, firstName, phone, email, password);
        this.enterprise = enterprise;
        this.programme = programme;
        this.internOffers = new ArrayList<>();
    }

    public EmployeurDto toDto() {
        return new EmployeurDto(this);
    }
}
