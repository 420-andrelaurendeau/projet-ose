package com.sap.ose.projetose;

import com.sap.ose.projetose.models.*;
import com.sap.ose.projetose.repositories.*;
import lombok.RequiredArgsConstructor;
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
    private final StudyProgramRepository studyProgramRepository;
    private final StudentRepository studentRepository;
    private final EmployerRepository employerRepository;
    private final InternshipApplicationRepository internshipApplicationRepository;
    private final FileRepository fileRepository;
    private final InternshipManagerRepository internshipManagerRepository;
    private final InternshipOfferRepository internshipOfferRepository;
    private final OfferReviewRequestRepository offerReviewRequestRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjetOseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        StudyProgram studyProgramInformatique = studyProgramRepository.save(new StudyProgram("Techniques de l'informatique", "Programme de formation en techniques de l'informatique"));
        StudyProgram studyProgramAdmnistration = studyProgramRepository.save(new StudyProgram("Techniques de l'administration", "Programme de formation en techniques de l'administration"));
        StudyProgram studyProgramLogistique = studyProgramRepository.save(new StudyProgram("Techniques de la logistique", "Programme de formation en techniques de la logistique"));
        StudyProgram studyProgramComtabiliteGestion = studyProgramRepository.save(new StudyProgram("Techniques de la comptabilité et de la gestion", "Programme de formation en techniques de la comptabilité et de la gestion"));

        Student studentMarcMax = studentRepository.save(new Student("Marc", "Max", "4387999889", "max@gmail.com", "popo", "2045888", studyProgramInformatique, null));
        Student studentLoicLac = studentRepository.save(new Student("Loic", "Lac", "4352996589", "Lac@gmail.com", "popo", "2045898", studyProgramInformatique, null));
        Student studentJeanDupont = new Student("Jean", "Dupont", "4387996589", "dupont@gmail.com", "popo", "2045878", studyProgramInformatique, new ArrayList<>());


        Employer employerPatriqueLemieux = employerRepository.save(new Employer("Patrique", "Lemieux", "lemieux@gmail.com", "4383006589", "popo123", "popo", studyProgramInformatique));
        Employer employerPierreLacroix = employerRepository.save(new Employer("Pierre", "Lacroix", "lacroix@gmail.com", "4387996589", "popo123", "poo", studyProgramAdmnistration));

        InternshipManager internshipmanager = internshipManagerRepository.save(new InternshipManager("Jean", "Dupont", "4387996589", "dupont@gmail.com", "popo", studyProgramInformatique));

        OfferReviewRequest offerReviewRequest = offerReviewRequestRepository.save(new OfferReviewRequest());

        File fileApplicationMarc = fileRepository.save(new File("Application".getBytes(StandardCharsets.UTF_8), "Application", true, studentMarcMax));
        File fileOffreInformatique = fileRepository.save(new File("OffreInformatique".getBytes(StandardCharsets.UTF_8), "OffreInformatique", true, employerPatriqueLemieux));
        File fileOffreAdministration = fileRepository.save(new File("OffreSecurite".getBytes(StandardCharsets.UTF_8), "OffreSecurite", true, studentMarcMax));

        InternshipOffer internshipOfferInformatique = internshipOfferRepository.save(new InternshipOffer("Stage Informatique", "Laval", "ff", 20, LocalDate.now(), LocalDate.now(), new ArrayList<>(), studyProgramInformatique, fileOffreInformatique, employerPatriqueLemieux, ApprovalStatus.APPROVED, offerReviewRequest));
        InternshipOffer internshipOfferSecurite = internshipOfferRepository.save(new InternshipOffer("Stage Securité", "Montreal", "ff", 20, LocalDate.now(), LocalDate.now(), new ArrayList<>(), studyProgramInformatique, fileOffreAdministration, employerPierreLacroix, ApprovalStatus.APPROVED, offerReviewRequest));

        InternshipApplication internshipApplicationMax = internshipApplicationRepository.save(new InternshipApplication(studentMarcMax, internshipOfferInformatique, List.of(fileApplicationMarc)));
    }
}
