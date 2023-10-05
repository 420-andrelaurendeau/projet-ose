package com.sap.ose.projetose;

import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.InternshipApplicationDto;
import com.sap.ose.projetose.dto.InternshipManagerDto;
import com.sap.ose.projetose.models.*;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.UserService;
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
    private UserService userService;
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
    ProgrammeRepository programmeRepository;

    public static void main(String[] args) {
		SpringApplication.run(ProjetOseApplication.class, args);
	}
    @Override
    public void run(String... args) throws Exception {
        Program program1 = programmeRepository.save(new Program("Techniques de l'informatique", "Programme de formation en techniques de l'informatique"));
        Program program2 = programmeRepository.save(new Program("Techniques de l'administration", "Programme de formation en techniques de l'administration"));
        Program program3 = programmeRepository.save(new Program("Techniques de la logistique", "Programme de formation en techniques de la logistique"));
        Program program4 = programmeRepository.save(new Program("Techniques de la comptabilité et de la gestion", "Programme de formation en techniques de la comptabilité et de la gestion"));

        Student student2 = new Student("Marc", "Max", "4387999889", "max@gmail.com", "popo", "2045888", program1, null);
        Student student3 = new Student("Loic", "Lac", "4352996589", "Lac@gmail.com", "popo", "2045898", program1, null);
        Employer employer = new Employer("Patrique", "Lemieux", "lemieux@gmail.com","4383006589" ,"popo123", "popo", program1);
        Employer employer2 = new Employer("Pierre", "Lacroix", "lacroix@gmail.com","4387996589","popo123", "poo", program2);
        etudiantService.saveEtudiant(student2);
        etudiantService.saveEtudiant(student3);
        employeurService.saveEmployeur(employer);
        employeurService.saveEmployeur(employer2);

        File file = new File(1L,"hello".getBytes(StandardCharsets.UTF_8),"Test",true, null, null);
        List<InternshipApplication> internshipCandidates = new ArrayList<>();
        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();


        InternshipOffer internshipOffer = new InternshipOffer(1L, "Stage Informatique","Laval","ff",20,LocalDate.now(),LocalDate.now(),internshipCandidates, program1,file, employer, ApprovalStatus.APPROVED,offerReviewRequest);
        InternshipOfferDto internshipOfferDto = new InternshipOfferDto(internshipOffer);
        internOfferService.saveInterOfferJob(internshipOfferDto);

        InternshipOffer internshipOffer1 = new InternshipOffer("Stage Securité","Montreal","ff",20,LocalDate.now(),LocalDate.now(),internshipCandidates, program1,file, employer2, ApprovalStatus.APPROVED,offerReviewRequest);
        InternshipOfferDto internshipOfferDto1 = new InternshipOfferDto(internshipOffer1);
        internOfferService.saveInterOfferJob(internshipOfferDto1);

        Student student = new Student("Jean", "Dupont", "4387996589", "dupont@gmail.com", "popo", "2045878", program1, null);
        etudiantService.saveEtudiant(student);

        InternshipApplication internshipApplication1 = new InternshipApplication(student, internshipOffer, List.of(file));

        internshipCandidatesService.saveCandidates(new InternshipApplicationDto(internshipApplication1));


        InternshipManager internshipmanager = new InternshipManager(1L, "Jean", "Dupont", "4387996589",  "dupont@gmail.com", "popo", program1);
        internshipmanagerService.save(new InternshipManagerDto(internshipmanager));
    }
}
