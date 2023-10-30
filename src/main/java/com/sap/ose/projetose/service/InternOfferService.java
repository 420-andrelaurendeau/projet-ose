package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.NewInternshipOfferDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log
@Service
@RequiredArgsConstructor
public class InternOfferService {

    private final InternOfferRepository internOfferRepository;
    private final EmployeurRepository employeurRepository;
    private final ProgrammeService programmeService;
    private final EmployeurService employeurService;
    private final OfferReviewRequestService offerReviewRequestService;
    private final FileService fileService;


    @Transactional
    public InternOfferDto createInternshipOffer(@Valid NewInternshipOfferDto internshipOfferDto) {
        InternOffer internOffer = new InternOffer();

        internOffer.setTitle(internshipOfferDto.getTitle());
        internOffer.setLocation(internshipOfferDto.getLocation());
        internOffer.setDescription(internshipOfferDto.getDescription());
        internOffer.setSalaryByHour(internshipOfferDto.getSalaryByHour());
        internOffer.setStartDate(internshipOfferDto.getStartDate());
        internOffer.setEndDate(internshipOfferDto.getEndDate());
        internOffer.setProgramme(programmeService.findProgramById(internshipOfferDto.getProgramId()));
        internOffer.setFile(fileService.getFileById(internshipOfferDto.getOfferFile()));
        internOffer.setEmployeur(employeurService.findById(internshipOfferDto.getEmployerId()));
        internOffer.setState(Etats.PENDING);

        internOfferRepository.save(internOffer);

        internOffer.setOfferReviewRequest(offerReviewRequestService.createRequest(internOffer));


        InternOffer savedOfferDto = internOfferRepository.save(internOffer);

        return new InternOfferDto(savedOfferDto);
    }

    @Transactional
    public List<InternOfferDto> getAcceptedInternshipOffer() {
        List<InternOffer> internOfferList = internOfferRepository.findAllByStateIsApproved();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();

        for (InternOffer offre : internOfferList) {
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    @Transactional
    public List<InternOfferDto> getInternOfferPending() {
        List<InternOffer> internOfferList = internOfferRepository.findAllByStateIsPending();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();

        for (InternOffer offre : internOfferList) {
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    InternOffer findById(long id) {
        return internOfferRepository.findById(id).orElseThrow(OfferNotFoundException::new);
    }

    public List<InternOfferDto> getAllInternOffers() {
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();
        for (InternOffer offer : internOfferRepository.findAll()) {
            internOfferDtoList.add(new InternOfferDto(offer));
        }
        return internOfferDtoList;
    }

    boolean isApprovedOrDeclineById(long id) {
        return internOfferRepository.findById(id).filter(offer -> offer.getState() == Etats.APPROVED || offer.getState() == Etats.REJECTED).isPresent();
    }

    @Transactional
    public List<InternOfferDto> getInternOfferByEmployerEmail(String email) {
        List<InternOfferDto> internOfferDtos = new ArrayList<>();
        List<InternOffer> internOffers = employeurRepository.findAllByEmailEqualsIgnoreCase(email).map(Employeur::getInternOffers).orElse(null);

        if (internOffers == null) {
            return internOfferDtos;
        }

        for (InternOffer internOffer : internOffers) {
            internOfferDtos.add(new InternOfferDto(internOffer));
        }

        return internOfferDtos;
    }

}