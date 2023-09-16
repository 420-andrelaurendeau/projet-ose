package com.sap.ose.projetose;

import com.sap.ose.projetose.model.Employeur;
import com.sap.ose.projetose.model.Etudiant;
import com.sap.ose.projetose.service.EmployeurService;
import com.sap.ose.projetose.service.EtudiantService;
import com.sap.ose.projetose.service.OseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetOseApplication implements CommandLineRunner {

	@Autowired
	private EtudiantService etudiantService;
	@Autowired
	private EmployeurService employeurService;
	public static void main(String[] args) {
		SpringApplication.run(ProjetOseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Etudiant etudiant = new Etudiant("Jean", "Dupont", "4387996589", "dupont@gmail.com", "popo", "2045878", "Informatique");
		Etudiant etudiant2 = new Etudiant("Marc", "Max", "4387999889", "max@gmail.com", "popo", "2045888", "Informatique");
		Etudiant etudiant3 = new Etudiant("Loic", "Lac", "4352996589", "Lac@gmail.com", "popo", "2045898", "Informatique");
		Employeur employeur = new Employeur("Patrique", "Lemieux", "4383006589", "lemieux@gmail.com", "popo", "SAP");
		Employeur employeur2 = new Employeur("Pierre", "Lacroix", "4387996589", "lacroix@gmail.com", "popo", "SAP");
		etudiantService.saveEtudiant(etudiant);
		etudiantService.saveEtudiant(etudiant2);
		etudiantService.saveEtudiant(etudiant3);
		employeurService.saveEmployeur(employeur);
		employeurService.saveEmployeur(employeur2);

	}

}
