package com.sap.ose.projetose;

import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.dto.auth.InternshipmanagerAuthDto;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.OseService;
import com.sap.ose.projetose.service.*;
import com.sap.ose.projetose.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjetOseApplication implements CommandLineRunner {

    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private EmployeurService employeurService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private InternOfferService internOfferService;

    @Autowired
    private  InternshipmanagerService internshipmanagerService;

    @Autowired
    private InternshipCandidatesService internshipCandidatesService;

    @Autowired
    private StageService stageService;
    @Autowired
    private InterviewService interviewService;

    @Autowired
    ProgrammeRepository programmeRepository;

    public static void main(String[] args) {
		SpringApplication.run(ProjetOseApplication.class, args);
	}
    @Override
    public void run(String... args) throws Exception {
        Programme programme1 = programmeRepository.save(new Programme("Techniques de l'informatique", "Programme de formation en techniques de l'informatique"));
        Programme programme2 = programmeRepository.save(new Programme("Techniques de l'administration", "Programme de formation en techniques de l'administration"));
        Programme programme3 = programmeRepository.save(new Programme("Techniques de la logistique", "Programme de formation en techniques de la logistique"));
        Programme programme4 = programmeRepository.save(new Programme("Techniques de la comptabilité et de la gestion", "Programme de formation en techniques de la comptabilité et de la gestion"));

        Etudiant etudiant2 = new Etudiant("Marc", "Max", "4387999889", "max@gmail.com", "popo", "2045888", programme1, null);
        Etudiant etudiant3 = new Etudiant("Loic", "Lac", "4352996589", "Lac@gmail.com", "popo", "2045898", programme1, null);
        Employeur employeur = new Employeur("Patrique", "Lemieux", "lemieux@gmail.com","4383006589" ,"popo123", "Cisco", programme1);
        Employeur employeur2 = new Employeur("Pierre", "Lacroix", "lacroix@gmail.com","4387996589","popo123", "Norton", programme2);

        authenticationService.registerEtudiant(etudiant2);
        authenticationService.registerEtudiant(etudiant3);
        authenticationService.registerEmployeur(employeur);
        authenticationService.registerEmployeur(employeur2);

        Internshipmanager internshipmanager = new Internshipmanager("Jean","Dupont", "4387996589",  "dupont@gmail.com", "popo", programme1);
        authenticationService.registerInternshipManager(new InternshipmanagerAuthDto(internshipmanager));

        File file = new File(1L,"hello".getBytes(StandardCharsets.UTF_8),"SignContract",true, null, null);
        List<InternshipCandidates> internshipCandidates = new ArrayList<>();


        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();

        InternOffer internOffer = new InternOffer(1L,"Stage Informatique","Laval","En tant que stagiaire en informatique chez Cisco, vous aurez l'opportunité de travailler au sein de notre équipe de professionnels de l'informatique, d'apprendre de nouvelles compétences",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto = new InternOfferDto(internOffer);
        internOfferService.saveInterOfferJob(internOfferDto);

        InternOffer internOffer1 = new InternOffer("IT Internship: Nurturing Tomorrow's Tech Wizards","Montreal, Quebec","Are you a student in the field of \"Technique de l'Informatique\" seeking an immersive learning experience in one of the most vibrant tech ecosystems in North America? Look no further! Our Montreal IT Internship offers a unique opportunity to gain hands-on experience, expand your skillset, and embark on an exciting journey into the world of technology.",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur2, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto1 = new InternOfferDto(internOffer1);
        internOfferService.saveInterOfferJob(internOfferDto1);


        InternOffer internOffer2 = new InternOffer("Stage Réseaux","Quebec","En tant que stagiaire en réseau chez Cisco, vous aurez l'opportunité de plonger dans le monde passionnant des réseaux informatiques et d'acquérir une expérience pratique précieuse.",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto2 = new InternOfferDto(internOffer2);
        internOfferService.saveInterOfferJob(internOfferDto2);

        InternshipCandidates internshipCandidates1 = new InternshipCandidates(etudiant2, internOffer, List.of(file));
        internshipCandidatesService.saveCandidates(new InternshipCandidatesDto(internshipCandidates1));

        StageDto stage = new StageDto(0L,1L, 1L,State.PENDING, State.ACCEPTED, 0L);
        StageDto stage2 = new StageDto(0L,2L, 2L, State.ACCEPTED, State.ACCEPTED, 0L);
        StageDto stage3 = new StageDto(0L,2L, 2L,State.DECLINED, State.ACCEPTED, 0L);
        StageDto stage4 = new StageDto(0L,2L, 2L,State.PENDING, State.DECLINED, 0L);

        stageService.saveTEST(stage);
        stageService.saveTEST(stage2);
        stageService.saveTEST(stage3);
        stageService.saveTEST(stage4);



        System.out.println("DONE");
    }
}
