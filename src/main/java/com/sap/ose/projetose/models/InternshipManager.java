package com.sap.ose.projetose.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternshipManager extends User {

    @OneToOne
    @JoinColumn(name = "programme_id")
    private Formation formation;

    public InternshipManager(String nom, String prenom, String phone, String email, String password, Formation formation) {
        super(nom, prenom, phone, email, password);
        this.formation = formation;
    }
    public InternshipManager(long id, String nom, String prenom, String phone, String email, String password, Formation formation) {
        super(id, nom, prenom, phone, email, password);
        this.formation = formation;
    }
}
