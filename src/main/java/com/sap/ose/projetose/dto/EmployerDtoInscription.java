package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Employeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerDtoInscription extends UtilisateurInscriptionDto {

        private String entreprise;
        private long programme_id;

        public EmployerDtoInscription(Employeur employeur) {
            super(employeur.getNom(), employeur.getPrenom(), employeur.getPhone(), employeur.getEmail(), employeur.getPassword());
            this.entreprise = employeur.getEntreprise();
            this.programme_id = employeur.getProgramme().getId();
        }
        public EmployerDtoInscription(String nom, String prenom,String password, String phone,  String email, String entreprise, long programme_id) {
            super(nom, prenom, phone, email, password);
            this.entreprise = entreprise;
            this.programme_id = programme_id;
        }

        public Employeur fromDto(){
            return new Employeur(this.getNom(), this.getPrenom(),  this.getEmail(), this.getPhone(), this.getPassword(),this.getEntreprise(), null);
        }
}
