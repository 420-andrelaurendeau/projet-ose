package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InterviewRepository;
import io.micrometer.observation.ObservationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {
    private final InterviewRepository interviewRepository;
    private final EtudiantService etudiantService;

    private final EmployeurService employeurService;

    private final EmployeurRepository employeurRepository;

    private final EtudiantRepository etudiantRepository;

    private final InternOfferRepository internOfferRepository;

    @Autowired
    public InterviewService(InternOfferRepository internOfferRepository,InterviewRepository interviewRepository, EtudiantService etudiantService, EmployeurService employeurService, EmployeurRepository employeurRepository, EtudiantRepository etudiantRepository) {
        this.internOfferRepository = internOfferRepository;
        this.etudiantRepository = etudiantRepository;
        this.employeurRepository = employeurRepository;
        this.interviewRepository = interviewRepository;
        this.etudiantService = etudiantService;
        this.employeurService = employeurService;
    }

    public Optional<InterviewDTO> saveInterview(InterviewRequestInDto interviewRequestInDto) {
        //TODO changer employeur DTO et etudiant DTO pour quil retorne l'ID des modeles quil represente si non le programme essaye de les dupliquer

        InternOffer internOffer = internOfferRepository.findById(interviewRequestInDto.getInternOfferId()).orElse(null);
        Etudiant etudiant = etudiantRepository.findById(interviewRequestInDto.getStudentId()).orElse(null);
        interviewRequestInDto.setState(State.PENDING);

        if (internOffer == null || etudiant == null) {
            System.out.println("InternOffer or Etudiant not found");
            return Optional.empty();
        } else {
            System.out.println("InternOffer and Etudiant found");
        }

        Interview interview = new Interview(etudiant, internOffer, interviewRequestInDto.getDate(), interviewRequestInDto.getDescription());

       interview = interviewRepository.save(interview);

       EtudiantDto etudiantDto = new EtudiantDto(etudiant);

       InternOfferDto internOfferDto = new InternOfferDto(internOffer);

        if (interview != null) {
            InterviewDTO returnInterviewDto = new InterviewDTO(interview.getId(), etudiantDto, internOfferDto, interview.getDate(), interview.getDescription(), interview.getState());
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
        return interviewRepository.findAll().stream().filter(interview -> interview.getStudent().getId() == studentId && interview.getInternshipOffer().getId() == employerId).findFirst().orElse(null) != null;
    }

    public List<InterviewDTO> getAllInterviews() {
        return interviewRepository.findAll().stream().map(interview -> new InterviewDTO(interview.getId(), new EtudiantDto(interview.getStudent()), new InternOfferDto(interview.getInternshipOffer()), interview.getDate(), interview.getDescription(), interview.getState())).toList();
    }

    public List<InterviewDTO> getInterviewsByStudentId(long studentId) {
        return interviewRepository.findAllByStudentId(studentId).isPresent() ? interviewRepository.findAllByStudentId(studentId).get().stream().map(interview -> new InterviewDTO(interview.getId(), new EtudiantDto(interview.getStudent()), new InternOfferDto(interview.getInternshipOffer()), interview.getDate(), interview.getDescription(), interview.getState())).toList() : null;
    }

    public Optional<Long> getInterviewsCountByStudentId(long studentId) {
        return interviewRepository.findAllByStudentId(studentId).isPresent() ? Optional.of((long) interviewRepository.findAllByStudentId(studentId).get().size()) : Optional.empty();
    }

    public Optional<Boolean> studentAcceptsInterviewByStudentId(long studentId, long interviewId) {
        Interview interview = interviewRepository.findById(interviewId).orElse(null);
        Etudiant etudiant = etudiantRepository.findById(studentId).orElse(null);
        if (interview != null && interview.getStudent().getId() == studentId) {
            interview.setState(State.ACCEPTED);
            interview.setStudent(etudiant);
            interviewRepository.save(interview);
            return Optional.of(true);
        }
        return Optional.of(false);
    }

    public Optional<Boolean> studentDeclineInterviewByStudentId(long studentId, long interviewId) {
        Interview interview = interviewRepository.findById(interviewId).orElse(null);
        if (interview != null && interview.getStudent().getId() == studentId) {
            interview.setState(State.DECLINED);
            interviewRepository.save(interview);
            return Optional.of(true);
        }
        return Optional.of(false);
    }
}
