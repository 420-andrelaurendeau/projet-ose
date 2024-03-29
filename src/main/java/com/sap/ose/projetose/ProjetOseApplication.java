package com.sap.ose.projetose;

import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.dto.auth.InternshipmanagerAuthDto;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.*;
import com.sap.ose.projetose.service.auth.AuthenticationService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@SpringBootApplication
public class ProjetOseApplication implements CommandLineRunner {
    private final AuthenticationService authenticationService;
    private final InternOfferService internOfferService;
    private final ProgrammeRepository programmeRepository;
    private final TemplateContractService templateContractService;
    private final EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(ProjetOseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Programme programme1 = programmeRepository.save(new Programme("Techniques de l'informatique", "Programme de formation en techniques de l'informatique"));
        Programme programme2 = programmeRepository.save(new Programme("Techniques de l'administration", "Programme de formation en techniques de l'administration"));
        Programme programme3 = programmeRepository.save(new Programme("Techniques de la logistique", "Programme de formation en techniques de la logistique"));
        Programme programme4 = programmeRepository.save(new Programme("Techniques de la comptabilité et de la gestion", "Programme de formation en techniques de la comptabilité et de la gestion"));

        Etudiant etudiant2 = new Etudiant("Marc", "Max", "4387999889", "max@vaneixus.com", "popo", "2045888", programme1, null);
        Etudiant etudiant3 = new Etudiant("Loic", "Lac", "4352996589", "lac@vaneixus.com", "popo", "2045898", programme1, null);
        Employeur employeur = new Employeur("Patrique", "Lemieux", "lemieux@vaneixus.com","4383006589" ,"popo123", "Cisco", programme1);
        Employeur employeur2 = new Employeur("Pierre", "Lacroix", "lacroix@vaneixus.com","4387996589","popo123", "Norton", programme2);

        authenticationService.registerEtudiant(etudiant2);
        authenticationService.registerEtudiant(etudiant3);
        authenticationService.registerEmployeur(employeur);
        authenticationService.registerEmployeur(employeur2);

        Internshipmanager internshipmanager = new Internshipmanager("Jean","Dupont", "4387996589",  "dupont@vaneixus.com", "popo", programme1);
        authenticationService.registerInternshipManager(new InternshipmanagerAuthDto(internshipmanager));

        File file = new File("hello".getBytes(StandardCharsets.UTF_8),"Test",State.PENDING, null, null);
        List<InternshipCandidates> internshipCandidates = new ArrayList<>();


        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();

        InternOffer internOffer = new InternOffer(1L,"Stage Informatique","Laval","En tant que stagiaire en informatique chez Cisco, vous aurez l'opportunité de travailler au sein de notre équipe de professionnels de l'informatique, d'apprendre de nouvelles compétences",20,LocalDate.of(2024,02,20),LocalDate.of(2024,02,20),internshipCandidates,programme1,file,employeur, State.PENDING,offerReviewRequest);
        InternOfferDto internOfferDto = new InternOfferDto(internOffer);
        internOfferService.saveInterOfferJob(internOfferDto);

        InternOffer internOffer1 = new InternOffer(2L,"Stage Securité","Montreal","En tant que stagiaire en sécurité informatique chez Norton, vous aurez l'opportunité de plonger dans le monde dynamique de la sécurité des systèmes d'information.",20,LocalDate.of(2024,07,20),LocalDate.of(2024,07,20),internshipCandidates,programme1,file,employeur2, State.PENDING,offerReviewRequest);
        InternOfferDto internOfferDto1 = new InternOfferDto(internOffer1);

        internOfferService.saveInterOfferJob(internOfferDto1);

        InternOffer internOffer2 = new InternOffer(3L,"Stage Réseaux","Quebec","En tant que stagiaire en réseau chez Cisco, vous aurez l'opportunité de plonger dans le monde passionnant des réseaux informatiques et d'acquérir une expérience pratique précieuse.",20,LocalDate.of(2024, 03,20),LocalDate.of(2024,04,20),internshipCandidates,programme1,file,employeur, State.PENDING,offerReviewRequest);
        InternOfferDto internOfferDto2 = new InternOfferDto(internOffer2);
        internOfferService.saveInterOfferJob(internOfferDto2);

//        InternshipCandidates internshipCandidates1 = new InternshipCandidates(etudiant2, internOffer, List.of(file));
//        internshipCandidatesService.saveCandidates(new InternshipCandidatesDto(internshipCandidates1));

        java.io.File filePDF = new java.io.File("src/main/java/com/sap/ose/projetose/contratTemplate.pdf");
        try {

            FileInputStream fis = new FileInputStream(filePDF);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, read);
            }
            fis.close();


            byte[] byteArray = bos.toByteArray();
            Base64.getEncoder().encodeToString(byteArray);

            TemplateContractDto templateContractDto = new TemplateContractDto(LocalDate.now().toString(), true, file.getId(), file.getFileName(), Base64.getEncoder().encodeToString(byteArray));
            templateContractService.save(templateContractDto);

            // Utiliser byteArray selon les besoins
        } catch (IOException e) {
            e.printStackTrace();
        }



//        StageDto stage = new StageDto(0L,1L, internOfferDto, State.ACCEPTED, State.PENDING, 0L);
//        StageDto stage2 = new StageDto(0L,2L, internOfferDto1, State.ACCEPTED, State.ACCEPTED, 0L);
//        StageDto stage3 = new StageDto(0L,2L, internOfferDto2,State.DECLINED, State.ACCEPTED, 0L);
//        StageDto stage4 = new StageDto(0L,2L, internOfferDto2,State.PENDING, State.DECLINED,  0L);
//
//        stageService.saveTEST(stage);
//        stageService.saveTEST(stage2);
//        stageService.saveTEST(stage3);
//        stageService.saveTEST(stage4);

        System.out.println("DONE");
    }
}
