package com.sap.ose.projetose;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.service.EmployeurService;
import com.sap.ose.projetose.service.EtudiantService;
import com.sap.ose.projetose.service.InternOfferService;
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
    private EtudiantService etudiantService;
    @Autowired
    private EmployeurService employeurService;
    @Autowired
    private InternOfferService internOfferService;
    @Autowired
    ProgrammeRepository programmeRepository;
    @Autowired
    InternOfferRepository internOfferRepository;

    public static void main(String[] args) {
		SpringApplication.run(ProjetOseApplication.class, args);
	}
    @Override
    public void run(String... args) throws Exception {
        Programme programme1 = programmeRepository.save(new Programme("Techniques de l'informatique", "Programme de formation en techniques de l'informatique"));
        Programme programme2 = programmeRepository.save(new Programme("Techniques de l'administration", "Programme de formation en techniques de l'administration"));
        Programme programme3 = programmeRepository.save(new Programme("Techniques de la logistique", "Programme de formation en techniques de la logistique"));
        Programme programme4 = programmeRepository.save(new Programme("Techniques de la comptabilité et de la gestion", "Programme de formation en techniques de la comptabilité et de la gestion"));
        Etudiant etudiant = new Etudiant("Jean", "Dupont", "4387996589", "dupont@gmail.com", "popo", "2045878", programme1);
        Etudiant etudiant2 = new Etudiant("Marc", "Max", "4387999889", "max@gmail.com", "popo", "2045888", programme1);
        Etudiant etudiant3 = new Etudiant("Loic", "Lac", "4352996589", "Lac@gmail.com", "popo", "2045898", programme1);
        Employeur employeur = new Employeur("Patrique", "Lemieux", "lemieux@gmail.com","4383006589" ,"popo123", "popo", programme1);
        Employeur employeur2 = new Employeur("Pierre", "Lacroix", "lacroix@gmail.com","4387996589","popo123", "popo", programme2);
        etudiantService.saveEtudiant(etudiant);
        etudiantService.saveEtudiant(etudiant2);
        etudiantService.saveEtudiant(etudiant3);
        employeurService.saveEmployeur(employeur);
        employeurService.saveEmployeur(employeur2);

        File file = new File("hello".getBytes(StandardCharsets.UTF_8),"Test",true);
        List<InternshipCandidates> internshipCandidates = new ArrayList<>();
        InternOffer internOffer = new InternOffer("ff","ff","ff",20.50,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur, State.PENDING);
        InternOfferDto internOfferDto = new InternOfferDto(internOffer);
        internOfferService.saveInterOfferJob(internOfferDto);
        System.out.println("Done");
    }
}
