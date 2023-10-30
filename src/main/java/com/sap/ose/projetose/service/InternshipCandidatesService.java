package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.dto.NewInternshipApplicationDto;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.repository.InternshipCandidatesRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipCandidatesService {
    private final InternshipCandidatesRepository internshipCandidatesRepository;
    private final InternOfferService internOfferService;
    private final FileService fileService;
    private final EtudiantService etudiantService;

    private final Logger logger = LoggerFactory.getLogger(InternshipCandidatesService.class);

    @Transactional
    public InternshipCandidatesDto createApplication(NewInternshipApplicationDto internshipApplicationDto) {
        Etudiant etudiant = etudiantService.getStudentById(internshipApplicationDto.getCandidateId());
        InternOffer internOffer = internOfferService.findById(internshipApplicationDto.getInternshipOfferDtoId());

        List<File> files = internshipApplicationDto.getFileTransferDtosId() == null
                ? new ArrayList<>()
                : internshipApplicationDto.getFileTransferDtosId()
                .stream()
                .map(fileService::getFileById)
                .toList();

        InternshipCandidates internshipCandidates = new InternshipCandidates(etudiant, internOffer, files);

        return new InternshipCandidatesDto(internshipCandidatesRepository.save(internshipCandidates));
    }
}
