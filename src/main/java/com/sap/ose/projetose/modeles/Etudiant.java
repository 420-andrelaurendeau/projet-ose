package com.sap.ose.projetose.modeles;


import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.UtilisateurDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Etudiant extends Utilisateur {
    @Column(unique = true)
    private String matricule;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<File> cvList;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<InternshipCandidates> internshipCandidates;
    @ManyToOne
    private Programme programme;

    public Etudiant(String lastName, String firstName, String phone, String email, String password, Programme programme, String matricule) {
        super(lastName, firstName, phone, email, password);
        this.matricule = matricule;
        this.programme = programme;
        this.cvList = new ArrayList<>();
        this.internshipCandidates = new ArrayList<>();
    }

    @Override
    public UtilisateurDto toDto() {
        return new EtudiantDto(this);
    }
}
