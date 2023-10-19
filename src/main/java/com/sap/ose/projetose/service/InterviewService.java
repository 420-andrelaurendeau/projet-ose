package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.InterviewDTO;
import com.sap.ose.projetose.dto.InterviewRequestInDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.Interview;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterviewService {
    private final InterviewRepository interviewRepository;
    private final EtudiantService etudiantService;

    private final EmployeurService employeurService;

    private final EmployeurRepository employeurRepository;

    private final EtudiantRepository etudiantRepository;

    @Autowired
    public InterviewService(InterviewRepository interviewRepository, EtudiantService etudiantService, EmployeurService employeurService, EmployeurRepository employeurRepository, EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
        this.employeurRepository = employeurRepository;
        this.interviewRepository = interviewRepository;
        this.etudiantService = etudiantService;
        this.employeurService = employeurService;
    }

    public Optional<InterviewDTO> saveInterview(InterviewRequestInDto interviewRequestInDto) {

//        EtudiantDto studentDto = etudiantService.getEtudiantById(interviewRequestInDto.getStudentId());
//
//        if (studentDto == null){
//            System.out.println("Student not found" + "id : " + interviewRequestInDto.getStudentId());
//            return Optional.empty();
//        } else{
//            System.out.println("Student found" + " id : " + studentDto.getMatricule());
//        }
//
//        EmployeurDto employeurDto = employeurService.getEmployeurById(interviewRequestInDto.getEmployeurId());
//
//        if (employeurDto == null){
//            System.out.println("Employeur not found");
//            return Optional.empty();
//        } else{
//            System.out.println("Employeur found" + "id : " + employeurDto.getId());
//        }
//        studentDto.setId(interviewRequestInDto.getStudentId());
//        employeurDto.setId(interviewRequestInDto.getEmployeurId());


        //TODO changer employeur DTO et etudiant DTO pour quil retorne l'ID des modeles quil represente si non le programme essaye de les dupliquer

        Employeur employeur = employeurRepository.findById(interviewRequestInDto.getEmployeurId()).orElse(null);
        Etudiant etudiant = etudiantRepository.findById(interviewRequestInDto.getStudentId()).orElse(null);

        InterviewDTO interviewDTO = new InterviewDTO(etudiant, employeur, interviewRequestInDto.getDate(), interviewRequestInDto.getDescription());

        System.out.println(interviewDTO);

        Interview interview = interviewRepository.save(interviewDTO.fromDto());

        if (interview != null) {
            InterviewDTO returnInterviewDto = new InterviewDTO(interview.getId(), null, null, interview.getDate(), interview.getDescription());
            return Optional.of(returnInterviewDto);
        }

        return Optional.empty();

    }

    public Interview getInterviewById(Long id) {
        return interviewRepository.findById(id).orElse(null);
    }

    public void deleteInterviewById(Long id) {
        interviewRepository.deleteById(id);
    }

    public Boolean studentHasInterviewWithEmployeur(Long studentId, Long employerId) {
        return interviewRepository.findAll().stream().filter(interview -> interview.getStudent().getId() == studentId && interview.getEmployeur().getId() == employerId).findFirst().orElse(null) != null;
    }
}
