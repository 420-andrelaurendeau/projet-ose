package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.StudentApplicationDto;
import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.exception.StudentNotFoundException;
import com.sap.ose.projetose.models.InternshipCandidates;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.repository.StudentRepository;
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
public class StudentService {
    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    @Transactional
    public Optional<Student> saveEtudiant(Student etudiant) {
        try {
            System.out.println(etudiant.getInternshipsCandidate());
            return Optional.of(studentRepository.save(etudiant));
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("ErrorPage lors de la sauvegarde de l'etudiant") {
            };
        }

    }

    public List<StudentDto> getEtudiants() {
        List<StudentDto> dtos = new ArrayList<>();
        for (Student etudiant : studentRepository.findAll()) {
            dtos.add(new StudentDto(etudiant.getNom(), etudiant.getPrenom(), etudiant.getPhone(), etudiant.getEmail(), etudiant.getMatricule(), etudiant.getFormation().getId(), etudiant.getCv(), etudiant.getInternshipsCandidate().stream().map(InternshipCandidates::getId).toList()));
        }
        return dtos;
    }

    public StudentDto getEtudiantById(Long id) {
        Optional<Student> etudiant = studentRepository.findById(id);
        return etudiant.map(value -> new StudentDto(value.getNom(), value.getPrenom(), value.getPhone(), value.getEmail(), value.getMatricule(), value.getFormation().getId(), value.getCv(), value.getInternshipsCandidate().stream().map(InternshipCandidates::getId).toList())).orElse(null);
    }

    public Student findEtudiantById(Long id) {
        Optional<Student> etudiant = studentRepository.findById(id);
        return etudiant.orElse(null);
    }

    Student getEtudiantByCourriel(String courriel) {
        return studentRepository.findAllByEmailEqualsIgnoreCase(courriel).orElse(null);
    }

    @Transactional
    public List<StudentApplicationDto> getOffersAppliedByEtudiant(long id) {
        try {
            Student etudiant = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
            List<InternshipCandidates> offersApplied = etudiant.getInternshipsCandidate();

            if (offersApplied == null)
                return new ArrayList<>();

            return offersApplied.stream().map(
                    (offerApplied) -> {
                        StudentApplicationDto dto = new StudentApplicationDto();

                        InternshipOfferDto offerDto = new InternshipOfferDto(offerApplied.getInternshipOffer());
                        offerDto.setInternshipCandidates(null);

                        List<FileDto> fileDtos = offerApplied.getFiles().stream().map(FileDto::new).toList();

                        dto.setAppliedOffer(offerDto);
                        dto.setAppliedFiles(fileDtos);

                        return dto;
                    }).toList();

        } catch (StudentNotFoundException e) {
            logger.error("Etudiant non trouvé avec l'id" + id, e);
            throw e;
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération des offres appliquées par l'étudiant avec l'Id :" + id, e);
            throw new DatabaseException("Erreur lors de la récupération des offres appliquées par l'étudiant");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des offres appliquées par l'étudiant avec l'id :" + id, e);
            throw new ServiceException("Erreur lors de la récupération des offres appliquées par l'étudiant");
        }
    }


}
