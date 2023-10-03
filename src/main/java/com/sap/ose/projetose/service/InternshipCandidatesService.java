package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.repository.InternshipCandidatesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InternshipCandidatesService {

    private final InternshipCandidatesRepository internshipCandidatesRepository;
    private final InternOfferService internOfferService;
    private final EtudiantService etudiantService;

    private final FileService fileService;

    private final Logger logger = LoggerFactory.getLogger(InternshipCandidatesService.class);

    public InternshipCandidatesService(InternshipCandidatesRepository internshipCandidatesRepository, InternOfferService internOfferService, EtudiantService etudiantService, FileService fileService) {
        this.internshipCandidatesRepository = internshipCandidatesRepository;
        this.internOfferService = internOfferService;
        this.etudiantService = etudiantService;
        this.fileService = fileService;
    }

    @Transactional
    public InternshipCandidatesDto saveCandidates(InternshipCandidatesDto internshipCandidatesDto){
        try{
            InternshipCandidates internshipCandidates = internshipCandidatesDto.fromDto();

            Etudiant etudiant = etudiantService.findEtudiantById(internshipCandidatesDto.getEtudiant_id());
            InternOffer internOffer = internOfferService.findById(internshipCandidatesDto.getInterOfferJob_id());
            List<File> files = internshipCandidatesDto.getFiles_id().stream().map(fileService::findById).toList();


            internshipCandidates.setEtudiant(etudiant);
            internshipCandidates.setInternOffer(internOffer);
            internshipCandidates.setFiles(files);

            return new InternshipCandidatesDto(internshipCandidatesRepository.save(internshipCandidates));

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du candidats") {};
        }
    }
}