package com.sap.ose.projetose;

import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.dto.InternshipmanagerDto;
import com.sap.ose.projetose.models.*;
import com.sap.ose.projetose.repository.InternshipOfferRepository;
import com.sap.ose.projetose.repository.FormationRepository;
import com.sap.ose.projetose.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjetOseApplication implements CommandLineRunner {
    @Autowired
    private StudentService studentService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private InternshipOfferService internshipOfferService;

    @Autowired
    private InternshipManagerService internshipmanagerService;

    @Autowired
    private InternshipCandidatesService internshipCandidatesService;

    @Autowired
    FormationRepository formationRepository;
    @Autowired
    InternshipOfferRepository internshipOfferRepository;

    public static void main(String[] args) {
		SpringApplication.run(ProjetOseApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Formation formation1 = formationRepository.save(new Formation("Techniques de l'informatique", "Formation de formation en techniques de l'informatique"));
        Formation formation2 = formationRepository.save(new Formation("Techniques de l'administration", "Formation de formation en techniques de l'administration"));
        Formation formation3 = formationRepository.save(new Formation("Techniques de la logistique", "Formation de formation en techniques de la logistique"));
        Formation formation4 = formationRepository.save(new Formation("Techniques de la comptabilité et de la gestion", "Formation de formation en techniques de la comptabilité et de la gestion"));

        Student etudiant2 = new Student("Marc", "Max", "4387999889", "max@gmail.com", "popo", "2045888", formation1, null);
        Student etudiant3 = new Student("Loic", "Lac", "4352996589", "Lac@gmail.com", "popo", "2045898", formation1, null);
        Employer employer = new Employer("Patrique", "Lemieux", "lemieux@gmail.com","4383006589" ,"popo123", "popo", formation1);
        Employer employer2 = new Employer("Pierre", "Lacroix", "lacroix@gmail.com","4387996589","popo123", "poo", formation2);
        studentService.saveEtudiant(etudiant2);
        studentService.saveEtudiant(etudiant3);
        employerService.saveEmployeur(employer);
        employerService.saveEmployeur(employer2);

        File file = new File(1L,"hello".getBytes(StandardCharsets.UTF_8),"Test",true);
        List<InternshipCandidates> internshipCandidates = new ArrayList<>();
        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        InternshipOffer internshipOffer = new InternshipOffer(1L,"ff","ff","ff",20.50,LocalDate.now(),LocalDate.now(),internshipCandidates, formation1,file, employer, AssessmentState.PENDING,offerReviewRequest);
        InternshipOfferDto internshipOfferDto = new InternshipOfferDto(internshipOffer);
        internshipOfferService.saveInterOfferJob(internshipOfferDto);

        Student etudiant = new Student("Jean", "Dupont", "4387996589", "dupont@gmail.com", "popo", "2045878", formation1, null);
        studentService.saveEtudiant(etudiant);


        InternshipCandidates internshipCandidates1 = new InternshipCandidates(etudiant, internshipOffer, List.of(file));

        internshipCandidatesService.saveCandidates(new InternshipCandidatesDto(internshipCandidates1));


        InternshipManager internshipmanager = new InternshipManager(1L, "Jean", "Dupont", "4387996589",  "dupont@gmail.com", "popo", formation1);
        internshipmanagerService.save(new InternshipmanagerDto(internshipmanager));

    }
}
