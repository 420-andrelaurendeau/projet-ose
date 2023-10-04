package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.InternshipCandidates;
import com.sap.ose.projetose.repository.InternshipCandidatesRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipCandidatesService {
    private final InternshipCandidatesRepository internshipCandidatesRepository;
    private final InternshipOfferService internshipOfferService;
    private final StudentService studentService;

    private final FileService fileService;

    private final Logger logger = LoggerFactory.getLogger(InternshipCandidatesService.class);

    @Transactional
    public InternshipCandidatesDto saveCandidates(InternshipCandidatesDto internshipCandidatesDto){
        try{
            InternshipCandidates internshipCandidates = internshipCandidatesDto.fromDto();

            Student etudiant = studentService.findEtudiantById(internshipCandidatesDto.getEtudiant_id());
            InternshipOffer internshipOffer = internshipOfferService.findById(internshipCandidatesDto.getInterOfferJob_id());
            List<File> files = internshipCandidatesDto.getFiles_id().stream().map(fileService::findById).toList();


            internshipCandidates.setEtudiant(etudiant);
            internshipCandidates.setInternshipOffer(internshipOffer);
            internshipCandidates.setFiles(files);

            return new InternshipCandidatesDto(internshipCandidatesRepository.save(internshipCandidates));

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du candidats") {};
        }
    }
}
