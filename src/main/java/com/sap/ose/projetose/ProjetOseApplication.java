package com.sap.ose.projetose;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InternshipmanagerDto;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.InternshipmanagerService;
import com.sap.ose.projetose.service.OseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjetOseApplication implements CommandLineRunner {

    @Autowired
    private OseService oseService;
    @Autowired
    private InternOfferService internOfferService;
    @Autowired
    ProgrammeRepository programmeRepository;
    @Autowired
    InternshipmanagerService internshipmanagerService;

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
        InternshipmanagerDto internshipmanager = new InternshipmanagerDto("Jean", "Dupont", "4387996589", "dadda", 9, 1);

        internshipmanagerService.save(internshipmanager);
        oseService.saveEtudiant(etudiant);
        oseService.saveEtudiant(etudiant2);
        oseService.saveEtudiant(etudiant3);
        oseService.saveEmployeur(employeur);
        oseService.saveEmployeur(employeur2);

        File file = new File("hello".getBytes(StandardCharsets.UTF_8),"Test",true);
        List<InternshipCandidates> internshipCandidates = new ArrayList<>();
        InternOffer internOffer = new InternOffer("ff","ff","ff",20.50,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur, State.PENDING, null);
        InternOfferDto internOfferDto = new InternOfferDto(internOffer);
        internOfferService.saveInterOfferJob(internOfferDto);
        System.out.println("Done");
    }
}
