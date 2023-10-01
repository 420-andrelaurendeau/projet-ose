package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.repository.InternshipCandidatesRepository;
import org.springframework.stereotype.Service;

@Service
public class InternshipCandidatesService {

    private final InternshipCandidatesRepository internshipCandidatesRepository;
    private final OseService oseService;
    private final InternOfferService internOfferService;


    public InternshipCandidatesService(InternshipCandidatesRepository internshipCandidatesRepository, OseService oseService, InternOfferService internOfferService) {
        this.internshipCandidatesRepository = internshipCandidatesRepository;
        this.oseService = oseService;
        this.internOfferService = internOfferService;
    }

    public InternshipCandidatesDto saveCandidates(InternshipCandidatesDto internshipCandidatesDto) {
        InternshipCandidates internshipCandidates = internshipCandidatesDto.fromDto();

        EtudiantDto etudiantDto = oseService.getEtudiantById(internshipCandidatesDto.getEtudiant_id());
        InternOffer internOfferDto = internOfferService.findById(internshipCandidatesDto.getInterOfferJob_id());

        internshipCandidates.setEtudiant(etudiantDto.fromDto());
        internshipCandidates.setInternOffer(internOfferDto);
        return new InternshipCandidatesDto(internshipCandidatesRepository.save(internshipCandidates));
    }
}
