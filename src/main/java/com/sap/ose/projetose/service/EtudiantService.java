package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.StudentApplicationsDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EtudiantNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.models.InternshipApplication;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.repository.EtudiantRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    private final EtudiantRepository etudiantRepository;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Transactional
    public Optional<Student> saveEtudiant(Student student) {
        try {
            System.out.println(student.getInternshipApplications());
            return Optional.of(etudiantRepository.save(student));
        } catch (DataAccessException e) {
            logger.info(e.getMessage(), e);
            throw new DataAccessException("Error lors de la sauvegarde de l'etudiant") {
            };
        }

    }

    public List<StudentDto> getEtudiants() {
        List<StudentDto> dtos = new ArrayList<>();
        for (Student student : etudiantRepository.findAll()) {
            dtos.add(new StudentDto(student.getLastName(), student.getFirstName(), student.getPhoneNumber(), student.getEmail(), student.getMatricule(), student.getProgram().getId(), student.getCvList().stream().map(File::getId).toList(), student.getInternshipApplications().stream().map(InternshipApplication::getId).toList()));
        }
        return dtos;
    }

    public StudentDto getEtudiantById(Long id) {
        Optional<Student> etudiant = etudiantRepository.findById(id);
        return etudiant.map(value -> new StudentDto(value.getLastName(), value.getFirstName(), value.getPhoneNumber(), value.getEmail(), value.getMatricule(), value.getProgram().getId(), value.getCvList().stream().map(File::getId).toList(), value.getInternshipApplications().stream().map(InternshipApplication::getId).toList())).orElse(null);
    }

    public Student findEtudiantById(Long id) {
        Optional<Student> etudiant = etudiantRepository.findById(id);
        return etudiant.orElse(null);
    }

    public Student findByMatricule(String matricule){
        Optional<Student> etudiant = etudiantRepository.findByMatricule(matricule);
        return etudiant.orElse(null);
    }

    public Student updateCVByMatricule(String matricule, File cv){
        Student student = findByMatricule(matricule);
        student.setCvList(List.of(cv));
        return student;
    }

    Student getEtudiantByCourriel(String courriel) {
        return etudiantRepository.findByCourriel(courriel).orElse(null);
    }

    @Transactional
    public List<StudentApplicationsDto> getOffersAppliedByEtudiant(long id) {
        try {
            Student student = etudiantRepository.findById(id).orElseThrow(EtudiantNotFoundException::new);
            List<InternshipApplication> offersApplied = student.getInternshipApplications();

            if (offersApplied == null)
                return new ArrayList<>();

            return offersApplied.stream().map(
                    (offerApplied) -> {
                        StudentApplicationsDto dto = new StudentApplicationsDto();

                        InternshipOfferDto offerDto = new InternshipOfferDto(offerApplied.getInternshipOffer());
                        offerDto.setInternshipApplicationIds(null);

                        List<FileDto> fileDtos = offerApplied.getFiles().stream().map(FileDto::new).toList();

                        dto.setAppliedOffer(offerDto);
                        dto.setAppliedFiles(fileDtos);

                        return dto;
                    }).toList();

        } catch (EtudiantNotFoundException e) {
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
