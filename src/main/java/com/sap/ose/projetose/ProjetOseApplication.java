package com.sap.ose.projetose;

import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.OseService;
import com.sap.ose.projetose.service.*;
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
    private OseService oseService;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private EmployeurService employeurService;
    @Autowired
    private InternOfferService internOfferService;

    @Autowired
    private  InternshipmanagerService internshipmanagerService;

    @Autowired
    private InternshipCandidatesService internshipCandidatesService;

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private ProgrammeRepository programmeRepository;

    @Autowired
    private OfferReviewRequestService offerReviewRequestService;

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
        etudiantService.saveEtudiant(etudiant2);
        etudiantService.saveEtudiant(etudiant3);
        employeurService.saveEmployeur(employeur);
        employeurService.saveEmployeur(employeur2);

        File file = new File(1L,"hello".getBytes(StandardCharsets.UTF_8),"Test",true, null, null);
        List<InternshipCandidates> internshipCandidates = new ArrayList<>();
        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();

        InternOffer internOffer = new InternOffer(1L,"Stage Informatique","Laval","En tant que stagiaire en informatique chez Cisco, vous aurez l'opportunité de travailler au sein de notre équipe de professionnels de l'informatique, d'apprendre de nouvelles compétences",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto = new InternOfferDto(internOffer);
        internOfferService.saveInterOfferJob(internOfferDto);

        InternOffer internOffer1 = new InternOffer("Stage Securité","Montreal","En tant que stagiaire en sécurité informatique chez Norton, vous aurez l'opportunité de plonger dans le monde dynamique de la sécurité des systèmes d'information.",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur2, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto1 = new InternOfferDto(internOffer1);
        internOfferService.saveInterOfferJob(internOfferDto1);

        InternOffer internOffer2 = new InternOffer("Stage Réseaux","Quebec","En tant que stagiaire en réseau chez Cisco, vous aurez l'opportunité de plonger dans le monde passionnant des réseaux informatiques et d'acquérir une expérience pratique précieuse.",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto2 = new InternOfferDto(internOffer2);
        internOfferService.saveInterOfferJob(internOfferDto2);


        Internshipmanager mangager = new Internshipmanager("Dupont", "Jean","4387996589", "dupont@gmail.com", "popo", programme1);
        mangager = internshipmanagerService.save(new InternshipmanagerDto(mangager));

//        mangager = internshipmanagerService.findById(mangager.getId());
//        OfferReviewRequest offerReviewRequest1 = new OfferReviewRequest(internOffer, "commentaire", mangager);
//        InternOffer offreDuReview = internOfferService.findById(offerReviewRequest1.getInternOffer().getId());
//        offreDuReview.setState(State.ACCEPTED);
//        offerReviewRequest1.setInternOffer(offreDuReview);
//        offerReviewRequestService.saveOfferReviewRequest(new OfferReviewRequestDto(offerReviewRequest1));
    }
}
