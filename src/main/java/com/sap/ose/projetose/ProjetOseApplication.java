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

    @Autowired InterviewService interviewService;

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
        Employeur employeur = new Employeur("Patrique", "Lemieux", "lemieux@gmail.com","4383006589" ,"popo123", "popo", programme1);
        Employeur employeur2 = new Employeur("Pierre", "Lacroix", "lacroix@gmail.com","4387996589","popo123", "poo", programme2);
        etudiantService.saveEtudiant(etudiant2);
        etudiantService.saveEtudiant(etudiant3);
        employeurService.saveEmployeur(employeur);
        employeurService.saveEmployeur(employeur2);

        File file = new File(1L,"hello".getBytes(StandardCharsets.UTF_8),"Test",true, null, null);
        List<InternshipCandidates> internshipCandidates = new ArrayList<>();
        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();

        InternOffer internOffer = new InternOffer(1L,"Stage Informatique","Laval","Description :\n",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto = new InternOfferDto(internOffer);
        internOfferService.saveInterOfferJob(internOfferDto);

        InternOffer internOffer1 = new InternOffer("Stage Securité","Montreal","ff",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur2, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto1 = new InternOfferDto(internOffer1);
        internOfferService.saveInterOfferJob(internOfferDto1);

        InternOffer internOffer4 = new InternOffer("Stage Securité","Montreal","ff",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur2, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto4 = new InternOfferDto(internOffer4);
        internOfferService.saveInterOfferJob(internOfferDto4);

        InternOffer internOffer5 = new InternOffer("Stage Securité","Montreal","ff",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur2, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto5 = new InternOfferDto(internOffer5);
        internOfferService.saveInterOfferJob(internOfferDto5);

        InternOffer internOffer6 = new InternOffer("Stage Securité","Montreal","fdushfkjdskjfhk dsfkjdskjfds vkj jfkjh fds l fhs ljds f ldsf ljds f dslfdsfldsfdsfljdsfbdskjfhbdsfljds fljds fdslhfsdhfsdlhfuiewhfiuewekwf fhewife f;iuew ufi;e ufi;ew fwe fw; fewuf; ew;i f;wie f;iew fee;wuhfuewhfh;iuehf;nweiuf;iufdskjf sdf;k ds;ifdsuif;dsiu;fkndsu;nfsdkjfdskjf d;sifbewu;ife wuiw;ef;ewuiiuefufewhfui;ewiufuifbbfiwlebflew fweifbewiufbibewfew eiuwfbiewfewfwebfil liewfbilbiuewlbfiuqif;bieq",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur2, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto6 = new InternOfferDto(internOffer6);
        internOfferService.saveInterOfferJob(internOfferDto6);



        InternOffer internOffer2 = new InternOffer("Stage Réseaux","Quebec","ff",20,LocalDate.now(),LocalDate.now(),internshipCandidates,programme1,file,employeur, State.ACCEPTED,offerReviewRequest);
        InternOfferDto internOfferDto2 = new InternOfferDto(internOffer2);
        internOfferService.saveInterOfferJob(internOfferDto2);


        Etudiant etudiant = new Etudiant("Jean", "Dupont", "4387996589", "dupont@gmail.com", "popo", "2045878", programme1, null);
        etudiantService.saveEtudiant(etudiant);



        Internshipmanager internshipmanager = new Internshipmanager(1L, "Jean", "Dupont", "4387996589",Role.GS,  "dupont@gmail.com", "popo", programme1);
        internshipmanagerService.save(new InternshipmanagerDto(internshipmanager));

        System.out.println("DONE");
    }
}
