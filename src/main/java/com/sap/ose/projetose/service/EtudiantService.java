package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.exception.StudentNotFoundException;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.EtudiantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final ProgrammeService programmeService;
    Logger logger = LoggerFactory.getLogger(EtudiantService.class);

    @Transactional
    public Optional<Etudiant> createStudent(Etudiant etudiant) {
        try {
            Programme programme = programmeService.findProgramById(etudiant.getProgramme().getId());
            etudiant.setProgramme(programme);
            return Optional.of(etudiantRepository.save(etudiant));
        } catch (DataAccessException e) {
            logger.info(e.getMessage(), e);
            throw new DataAccessException("Error lors de la sauvegarde de l'etudiant") {
            };
        }

    }

    public List<EtudiantDto> getStudents() {
        List<EtudiantDto> dtos = new ArrayList<>();

        for (Etudiant etudiant : etudiantRepository.findAll()) {
            dtos.add(new EtudiantDto(etudiant));
        }

        return dtos;
    }

    public EtudiantDto getStudentDTOById(Long id) {
        Optional<Etudiant> student = etudiantRepository.findById(id);
        return student.map(EtudiantDto::new).orElse(null);
    }

    public Etudiant getStudentById(Long id) {
        Optional<Etudiant> student = etudiantRepository.findById(id);
        return student.orElse(null);
    }

    public Etudiant updateCvById(Long id, File cv) {
        Etudiant etudiant = etudiantRepository.getReferenceById(id);
        etudiant.setCvList(List.of(cv));
        return etudiant;
    }

    Etudiant getStudentByEmail(String email) {
        return etudiantRepository.findAllByEmailEqualsIgnoreCase(email).orElse(null);
    }

    @Transactional
    public List<InternshipCandidatesDto> getApplicationsByStudent(long id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        List<InternshipCandidates> offersApplied = etudiant.getInternshipCandidates();

        if (offersApplied == null)
            return new ArrayList<>();

        return offersApplied.stream().map(
                (offerApplied) -> {
                    InternshipCandidatesDto dto = new InternshipCandidatesDto();

                    long offerDtoId = offerApplied.getInternOffer().getId();

                    List<Long> newFileTransferDtos = offerApplied.getFiles().stream().map(BaseModel::getId).toList();

                    dto.setInternshipOfferDtoId(offerDtoId);
                    dto.setFileTransferDtosId(newFileTransferDtos);

                    return dto;
                }).toList();
    }


}
