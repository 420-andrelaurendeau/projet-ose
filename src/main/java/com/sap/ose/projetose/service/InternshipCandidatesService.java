package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.repository.InternshipCandidatesRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class InternshipCandidatesService {

    private final InternshipCandidatesRepository internshipCandidatesRepository;
    private final InternOfferService internOfferService;
    private final EtudiantService etudiantService;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);


    public InternshipCandidatesService(InternshipCandidatesRepository internshipCandidatesRepository, InternOfferService internOfferService, EtudiantService etudiantService) {
        this.internshipCandidatesRepository = internshipCandidatesRepository;
        this.internOfferService = internOfferService;
        this.etudiantService = etudiantService;
    }

    @Transactional
    public InternshipCandidatesDto saveCandidates(InternshipCandidatesDto internshipCandidatesDto){
        try{
            InternshipCandidates internshipCandidates = internshipCandidatesDto.fromDto();

            EtudiantDto etudiantDto = etudiantService.getEtudiantById(internshipCandidatesDto.getEtudiant_id());
            InternOfferDto internOfferDto = internOfferService.getInterOfferById(internshipCandidatesDto.getInterOfferJob_id());

            internshipCandidates.setEtudiant(etudiantDto.fromDto());
            internshipCandidates.setInternOffer(internOfferDto.fromDto());
            return new InternshipCandidatesDto(internshipCandidatesRepository.save(internshipCandidates));

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du candidats") {};
        }
       }
}
