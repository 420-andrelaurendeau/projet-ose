package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.InternshipOfferDto;
import com.sap.ose.projetose.exceptions.*;
import com.sap.ose.projetose.models.*;
import com.sap.ose.projetose.repositories.EmployerRepository;
import com.sap.ose.projetose.repositories.InternshipOfferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InternshipOfferService {

    private final InternshipOfferRepository internshipOfferRepository;
    private final EmployerRepository employerRepository;
    private final StudyProgramService studyProgramService;
    private final EmployerService employerService;
    private final InternshipOfferReviewRequestService internshipOfferReviewRequestService;
    private final FileService fileService;
    Logger logger = LoggerFactory.getLogger(InternshipOfferService.class);


    @Transactional
    public InternshipOfferDto createOrUpdateInternshipOffer(InternshipOfferDto internshipOfferDto) {
        try {
            InternshipOffer internshipOffer = internshipOfferRepository.findById(internshipOfferDto.getId()).orElse(null);

            if (internshipOffer != null && internshipOffer.getState() != ApprovalStatus.PENDING)
                throw new OfferAlreadyReviewedException("L'offre a déjà été approuvée et ne peut pas être modifiée.");

            internshipOffer = toInternshipOffer(internshipOfferDto);

            InternshipOffer savedOfferDto = internshipOfferRepository.save(internshipOffer);

            return new InternshipOfferDto(savedOfferDto);
        } catch (OfferAlreadyReviewedException e) {
            logger.error("L'offre a déjà été approuvée et ne peut pas être modifiée pour l'Id : " + internshipOfferDto.getId(), e);
            throw e;
        } catch (ProgramNotFoundException e) {
            throw new ProgramNotFoundException();
        } catch (EmployerNotFoundException e) {
            throw new EmployerNotFoundException();
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la sauvegarde de l'offre d'emploi.", e);
            throw new DatabaseException("Erreur lors de la sauvegarde de l'offre d'emploi.");
        } catch (Exception e) {
            logger.error("Erreur inconnu lors de la sauvegarde de l'offre d'emploi.", e);
            throw new ServiceException("Erreur lors de la sauvegarde de l'offre d'emploi.");
        }
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
        try {
            return internshipOfferRepository.findById(id).orElseThrow(OfferNotFoundException::new);
        } catch (OfferNotFoundException e) {
            logger.error("Offre d'emploi non trouvée pour l'Id : " + id);
            throw new OfferNotFoundException();
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération de l'offre d'emploi avec l'ID : " + id, e);
            throw new DatabaseException("Erreur lors de la récupération de l'offre d'emploi.");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération de l'offre d'emploi avec l'ID : " + id, e);
            throw new ServiceException("Erreur lors de la récupération de l'offre d'emploi.");
        }
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

    public List<InternshipOfferDto> getInternOffer() {
        List<InternshipOfferDto> internshipOfferDtos = new ArrayList<>();
        for (InternshipOffer internshipOffer : internshipOfferRepository.findAll()) {
            internshipOfferDtos.add(new InternshipOfferDto(internshipOffer));
        }
        return internshipOfferDtos;
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

    public InternshipOffer toInternshipOffer(InternshipOfferDto internshipOfferDto) {
        StudyProgram studyProgram = studyProgramService.findProgramById(internshipOfferDto.getProgramId());
        Employer employer = employerService.findById(internshipOfferDto.getEmployerId());
        File file = fileService.newFile(internshipOfferDto.getFile());
        OfferReviewRequest offerReviewRequest = internshipOfferReviewRequestService.createNewRequest();

        InternshipOffer internshipOffer = internshipOfferRepository.findById(internshipOfferDto.getId()).orElse(null);

        internshipOffer = internshipOffer == null
                ? new InternshipOffer(
                internshipOfferDto.getTitle(),
                internshipOfferDto.getLocation(),
                internshipOfferDto.getDescription(),
                internshipOfferDto.getSalaryByHour(),
                LocalDate.parse(internshipOfferDto.getStartDate()),
                LocalDate.parse(internshipOfferDto.getEndDate()),
                new ArrayList<>(),
                studyProgram,
                file,
                employer,
                internshipOfferDto.getState(),
                offerReviewRequest
        )
                : internshipOffer;

        internshipOffer.setTitle(Objects.requireNonNullElse(internshipOfferDto.getTitle(), internshipOffer.getTitle()));
        internshipOffer.setLocation(Objects.requireNonNullElse(internshipOfferDto.getLocation(), internshipOffer.getLocation()));
        internshipOffer.setDescription(Objects.requireNonNullElse(internshipOfferDto.getDescription(), internshipOffer.getDescription()));
        internshipOffer.setSalaryByHour(internshipOfferDto.getSalaryByHour());
        internshipOffer.setStartDate(Objects.requireNonNullElse(LocalDate.parse(internshipOfferDto.getStartDate()), internshipOffer.getStartDate()));
        internshipOffer.setEndDate(Objects.requireNonNullElse(LocalDate.parse(internshipOfferDto.getEndDate()), internshipOffer.getEndDate()));
        internshipOffer.setStudyProgram(Objects.requireNonNullElse(studyProgram, internshipOffer.getStudyProgram()));
        internshipOffer.setFile(Objects.requireNonNullElse(file, internshipOffer.getFile()));
        internshipOffer.setEmployer(Objects.requireNonNullElse(employer, internshipOffer.getEmployer()));
        internshipOffer.setState(Objects.requireNonNullElse(internshipOfferDto.getState(), internshipOffer.getState()));
        internshipOffer.setOfferReviewRequest(Objects.requireNonNullElse(offerReviewRequest, internshipOffer.getOfferReviewRequest()));

        return internshipOffer;
    }
}