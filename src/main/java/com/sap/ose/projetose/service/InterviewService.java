package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.InterviewDTO;
import com.sap.ose.projetose.dto.InterviewRequestInDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Interview;
import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InterviewRepository;
import io.micrometer.observation.ObservationFilter;
import io.micrometer.observation.ObservationFilter;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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

    Logger logger = LoggerFactory.getLogger(InterviewService.class);

    @Autowired
    public InterviewService(InternOfferRepository internOfferRepository, InterviewRepository interviewRepository, EtudiantService etudiantService, EmployeurService employeurService, EmployeurRepository employeurRepository, EtudiantRepository etudiantRepository) {
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

    @Transactional
    public Optional<InterviewDTO> getInterview(long studentId, long internOfferId) {
        try {
            InterviewDTO interviewDTO = new InterviewDTO();
            Interview inte = interviewRepository.findByStudentIdAndInternOfferId(studentId, internOfferId);
            if (inte != null) {
                interviewDTO = new InterviewDTO(new EtudiantDto(inte.getStudent()), new InternOfferDto(inte.getInternshipOffer()), inte.getDate(), inte.getDescription(), inte.getState());
            }
            return Optional.of(interviewDTO);
        } catch (Exception e) {
            logger.error("Error while getting interview", e);
            return Optional.empty();
        }

    }

    public List<InterviewDTO> getAllInterviews() {
        return interviewRepository.findAll().stream().map(interview -> new InterviewDTO(interview.getId(), new EtudiantDto(interview.getStudent()), new InternOfferDto(interview.getInternshipOffer()), interview.getDate(), interview.getDescription(), interview.getState())).toList();
    }

    public Page<InterviewDTO> getInterviewsByStudentId(long studentId, int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Interview> page1 = interviewRepository.findAllByStudentId(studentId, pageable);

        return page1.map(
                interview -> new InterviewDTO(
                        interview.getId(),
                        new EtudiantDto(interview.getStudent()),
                        new InternOfferDto(interview.getInternshipOffer()),
                        interview.getDate(),
                        interview.getDescription(),
                        interview.getState()
                ));
    }

    public Optional<Long> getInterviewsCountByStudentId(long studentId) {
        int page = 1;
        int size = 10;
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return Optional.of(1L);
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


//    @Transactional
//    public Optional<InterviewDTO> getInterview(long studentId, long internOfferId) {
//        try {
//            InterviewDTO interviewDTO = new InterviewDTO();
//            Interview inte = interviewRepository.findByStudentIdAndInternOfferId(studentId, internOfferId);
//            if (inte != null) {
//                interviewDTO = new InterviewDTO(inte.getId(), null, null, inte.getDate(), inte.getDescription(), inte.getState());
//            }
//            return Optional.of(interviewDTO);
//        }catch (Exception e){
//            logger.error("Error while getting interview",e);
//            return Optional.empty();
//        }
//
//    }
}
