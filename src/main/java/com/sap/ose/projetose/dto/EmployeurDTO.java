package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Employeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeurDTO {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String phone;
    private String password;
    private String nomEntreprise;
    private int programme;

    public EmployeurDTO(Employeur employeur){
        this.id = employeur.getId();
        this.nom = employeur.getNom();
        this.prenom = employeur.getPrenom();
        this.email = employeur.getEmail();
        this.phone = employeur.getPhone();
        this.password = employeur.getPassword();
        this.nomEntreprise = employeur.getNomEntreprise();
        this.programme = employeur.getProgramme();
    }

    public Employeur fromDTO(){
        return new Employeur(
                getId(),
                getNom(),
                getPrenom(),
                getEmail(),
                getPhone(),
                getPassword(),
                getNomEntreprise(),
                getProgramme());
    }
}
