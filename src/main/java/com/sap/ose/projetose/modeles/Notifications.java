package com.sap.ose.projetose.modeles;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notifications {
    @Id
    private Long id;
    @OneToMany
    private List<Utilisateur> receveurs;
    private String message;
    private boolean read;
}
