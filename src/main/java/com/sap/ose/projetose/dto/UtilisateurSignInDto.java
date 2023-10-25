package com.sap.ose.projetose.dto;
import com.sap.ose.projetose.modeles.Utilisateur;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtilisateurSignInDto {
        private long id;
        private String nom;
        private String prenom;
        private String phone;
        private String email;
        private String matricule;
        private String entreprise;
        private Long programme_id;

        public UtilisateurSignInDto(long id, String nom, String prenom, String phone, String email, String matricule, String entreprise, Long programme) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.phone = phone;
            this.email = email;
            this.matricule = matricule;
            this.entreprise = entreprise;
            this.programme_id = programme;
        }

        public UtilisateurSignInDto(String nom, String prenom, String phone, String email, String matricule, String entreprise, Long programme) {
            this.nom = nom;
            this.prenom = prenom;
            this.phone = phone;
            this.email = email;
            this.matricule = matricule;
            this.entreprise = entreprise;
            this.programme_id = programme;
        }

        public UtilisateurSignInDto(Utilisateur utilisateur) {
            this.id = utilisateur.getId();
            this.nom = utilisateur.getNom();
            this.prenom = utilisateur.getPrenom();
            this.phone = utilisateur.getPhone();
            this.email = utilisateur.getEmail();
            this.matricule = utilisateur.getClass().getSimpleName().equals("Etudiant") ? ((com.sap.ose.projetose.modeles.Etudiant) utilisateur).getMatricule() : null;
            if (utilisateur.getClass().getSimpleName().equals("Employeur")) {
                assert utilisateur instanceof com.sap.ose.projetose.modeles.Employeur;
                this.entreprise = ((com.sap.ose.projetose.modeles.Employeur) utilisateur).getEntreprise();
            } else {
                this.entreprise = null;
            }
            String type = utilisateur.getClass().getSimpleName();
            if (type.equals("Employeur")) {
                assert utilisateur instanceof com.sap.ose.projetose.modeles.Employeur;
                this.programme_id = ((com.sap.ose.projetose.modeles.Employeur) utilisateur).getProgramme().getId();
            } else {
                this.programme_id = null;
            }
        }
    }

