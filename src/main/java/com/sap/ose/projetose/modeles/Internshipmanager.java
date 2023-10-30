package com.sap.ose.projetose.modeles;

import com.sap.ose.projetose.dto.InternshipmanagerDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Internshipmanager extends Utilisateur {
    @ManyToMany
    private Set<Programme> programmes;

    public Internshipmanager(String lastName, String firstName, String phone, String email, String password, Set<Programme> programmes) {
        super(lastName, firstName, phone, email, password);
        this.programmes = programmes;
    }

    public InternshipmanagerDto toDto() {
        return new InternshipmanagerDto(this);
    }
}
