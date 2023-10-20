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

        public EmployerDtoInscription(String nom, String prenom,String password, String phone,  String email, String entreprise, long programme_id) {
            super(nom, prenom, phone, email, password);
            this.entreprise = entreprise;
            this.programme_id = programme_id;
        }

        public Employeur fromDto(){
            return new Employeur(this.getNom(), this.getPrenom(),this.getPhone(),  this.getEmail(), this.getPassword(),this.getEntreprise());
        }
}
