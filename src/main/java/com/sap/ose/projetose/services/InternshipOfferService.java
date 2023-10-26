package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.InternshipOfferDto;
import com.sap.ose.projetose.dtos.NewInternshipOfferDto;
import com.sap.ose.projetose.exceptions.*;
import com.sap.ose.projetose.models.*;
import com.sap.ose.projetose.repositories.EmployerRepository;
import com.sap.ose.projetose.repositories.InternshipOfferRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log
@Service
@RequiredArgsConstructor
public class InternshipOfferService {

    private final InternshipOfferRepository internshipOfferRepository;
    private final EmployerRepository employerRepository;
    private final StudyProgramService studyProgramService;
    private final EmployerService employerService;
    private final InternshipOfferReviewRequestService internshipOfferReviewRequestService;
    private final FileService fileService;


    @Transactional
    public InternshipOfferDto createInternshipOffer(@Valid NewInternshipOfferDto internshipOfferDto) {
        InternshipOffer internshipOffer = new InternshipOffer();

        internshipOffer.setTitle(internshipOfferDto.getTitle());
        internshipOffer.setLocation(internshipOfferDto.getLocation());
        internshipOffer.setDescription(internshipOfferDto.getDescription());
        internshipOffer.setSalaryByHour(internshipOfferDto.getSalaryByHour());
        internshipOffer.setStartDate(internshipOfferDto.getStartDate());
        internshipOffer.setEndDate(internshipOfferDto.getEndDate());
        internshipOffer.setStudyProgram(studyProgramService.findProgramById(internshipOfferDto.getProgramId()));
        internshipOffer.setFile(fileService.getFileById(internshipOfferDto.getOfferFile()));
        internshipOffer.setEmployer(employerService.findById(internshipOfferDto.getEmployerId()));
        internshipOffer.setState(ApprovalStatus.PENDING);

        internshipOfferRepository.save(internshipOffer);

        internshipOffer.setOfferReviewRequest(internshipOfferReviewRequestService.createRequest(internshipOffer));


        InternshipOffer savedOfferDto = internshipOfferRepository.save(internshipOffer);

        return new InternshipOfferDto(savedOfferDto);
    }

    @Transactional
    public List<InternshipOfferDto> getAcceptedInternshipOffer() {
        List<InternshipOffer> internshipOfferList = internshipOfferRepository.findAllByStateIsApproved();
        List<InternshipOfferDto> internshipOfferDtoList = new ArrayList<>();

        for (InternshipOffer offre : internshipOfferList) {
            InternshipOfferDto internshipOfferDto = new InternshipOfferDto(offre);
            internshipOfferDtoList.add(internshipOfferDto);
        }
        return internshipOfferDtoList;
    }

    @Transactional
    public List<InternshipOfferDto> getInternOfferPending() {
        List<InternshipOffer> internshipOfferList = internshipOfferRepository.findAllByStateIsPending();
        List<InternshipOfferDto> internshipOfferDtoList = new ArrayList<>();

        for (InternshipOffer offre : internshipOfferList) {
            InternshipOfferDto internshipOfferDto = new InternshipOfferDto(offre);
            internshipOfferDtoList.add(internshipOfferDto);
        }
        return internshipOfferDtoList;
    }

    InternshipOffer findById(long id) {
        return internshipOfferRepository.findById(id).orElseThrow(OfferNotFoundException::new);
    }

    public List<InternshipOfferDto> getAllInternOffers() {
        List<InternshipOfferDto> internshipOfferDtoList = new ArrayList<>();
        for (InternshipOffer offer : internshipOfferRepository.findAll()) {
            internshipOfferDtoList.add(new InternshipOfferDto(offer));
        }
        return internshipOfferDtoList;
    }

    boolean isApprovedOrDeclineById(long id) {
        return internshipOfferRepository.findById(id).filter(offer -> offer.getState() == ApprovalStatus.APPROVED || offer.getState() == ApprovalStatus.REJECTED).isPresent();
    }

    @Transactional
    public List<InternshipOfferDto> getInternOfferByEmployerEmail(String email) {
        List<InternshipOfferDto> internshipOfferDtos = new ArrayList<>();
        List<InternshipOffer> internshipOffers = employerRepository.findAllByEmailEqualsIgnoreCase(email).map(Employer::getInternshipOffers).orElse(null);

        if (internshipOffers == null) {
            return internshipOfferDtos;
        }

        for (InternshipOffer internshipOffer : internshipOffers) {
            internshipOfferDtos.add(new InternshipOfferDto(internshipOffer));
        }

        return internshipOfferDtos;
    }

}