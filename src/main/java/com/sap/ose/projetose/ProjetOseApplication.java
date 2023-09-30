package com.sap.ose.projetose;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.OseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ProjetOseApplication implements CommandLineRunner {

    @Autowired
    ProgrammeRepository programmeRepository;
    @Autowired
    private OseService oseService;
    @Autowired
    private InternOfferService internOfferService;

    public static void main(String[] args) {
		SpringApplication.run(ProjetOseApplication.class, args);
	}
    @Override
    public void run(String... args) throws Exception {
        Programme programme1 = programmeRepository.save(new Programme("Techniques de l'informatique", "Programme de formation en techniques de l'informatique"));
        Programme programme2 = programmeRepository.save(new Programme("Techniques de l'administration", "Programme de formation en techniques de l'administration"));
        Programme programme3 = programmeRepository.save(new Programme("Techniques de la logistique", "Programme de formation en techniques de la logistique"));
        Programme programme4 = programmeRepository.save(new Programme("Techniques de la comptabilité et de la gestion", "Programme de formation en techniques de la comptabilité et de la gestion"));
        Etudiant etudiant = new Etudiant("Jean", "Dupont", "4387996589", "dupont@gmail.com", "popo", "2045878", "Informatique");
        Etudiant etudiant2 = new Etudiant("Marc", "Max", "4387999889", "max@gmail.com", "popo", "2045888", "Informatique");
        Etudiant etudiant3 = new Etudiant("Loic", "Lac", "4352996589", "Lac@gmail.com", "popo", "2045898", "Informatique");
        Employeur employeur = new Employeur("Patrique", "Lemieux", "4383006589", "lemieux@gmail.com", "popo", "SAaP");
        Employeur employeur2 = new Employeur("Pierre", "Lacroix", "4387996589", "lacroix@gmail.com", "popo", "SAP");
        File file = new File("test.pdf".getBytes(), "pdf",true);
        File file2 = new File("test2.pdf".getBytes(), "pdf",true);
        File file3 = new File("test3.pdf".getBytes(), "pdf",true);
        InternOffer internOffer = new InternOffer(1,"Stage en informatique", "Montreal", "the great job of the time", 15.5, LocalDate.parse("2021-05-02"), LocalDate.parse("2021-08-02"), false ,null ,programme1,file,employeur);
        InternOffer internOffer2 = new InternOffer(2,"Stage en informatique", "Montreal", "the great job of the time", 15.5, LocalDate.parse("2021-05-02"), LocalDate.parse("2021-08-02"), false, null, programme2,file2,employeur);
        InternOffer internOffer3 = new InternOffer(3,"Stage en informatique", "Montreal", "the great job of the time", 15.5, LocalDate.parse("2021-05-02"), LocalDate.parse("2021-08-02"), false, null, programme3,file3,employeur2);

        oseService.saveEtudiant(etudiant);
        oseService.saveEtudiant(etudiant2);
        oseService.saveEtudiant(etudiant3);
        oseService.saveEmployeur(employeur);
        oseService.saveEmployeur(employeur2);
        internOfferService.saveInterOfferJob(new InternOfferDto(internOffer));
        internOfferService.saveInterOfferJob(new InternOfferDto(internOffer2));
        internOfferService.saveInterOfferJob(new InternOfferDto(internOffer3));
    }
}
