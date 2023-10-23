package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.NewFileTransferDto;
import com.sap.ose.projetose.dtos.InternshipOfferDto;
import com.sap.ose.projetose.dtos.StudentApplicationDto;
import com.sap.ose.projetose.dtos.StudentDto;
import com.sap.ose.projetose.exceptions.DatabaseException;
import com.sap.ose.projetose.exceptions.ServiceException;
import com.sap.ose.projetose.exceptions.StudentNotFoundException;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.InternshipApplication;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.models.StudyProgram;
import com.sap.ose.projetose.repositories.StudentRepository;
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
    private final StudyProgramService studyProgramService;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Transactional
    public Optional<Student> saveStudent(Student student) {
        try {
            StudyProgram studyProgram = studyProgramService.findProgramById(student.getStudyProgram().getId());
            student.setStudyProgram(studyProgram);
            return Optional.of(studentRepository.save(student));
        } catch (DataAccessException e) {
            logger.info(e.getMessage(), e);
            throw new DataAccessException("Error lors de la sauvegarde de l'etudiant") {
            };
        }

    }

    public List<StudentDto> getStudents() {
        List<StudentDto> dtos = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            dtos.add(new StudentDto(student.getLastName(), student.getFirstName(), student.getPhoneNumber(), student.getEmail(), student.getMatricule(), student.getStudyProgram().getId(), student.getCvList().stream().map(File::getId).toList(), student.getInternshipApplications().stream().map(InternshipApplication::getId).toList()));
        }
        return dtos;
    }

    public StudentDto getStudentDTOById(Long id) {
        Optional<Student> etudiant = studentRepository.findById(id);
        return etudiant.map(value -> new StudentDto(value.getLastName(), value.getFirstName(), value.getPhoneNumber(), value.getEmail(), value.getMatricule(), value.getStudyProgram().getId(), value.getCvList().stream().map(File::getId).toList(), value.getInternshipApplications().stream().map(InternshipApplication::getId).toList())).orElse(null);
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student getStudentByMatricule(String matricule) {
        Optional<Student> etudiant = studentRepository.findByMatriculeEqualsIgnoreCase(matricule);
        return etudiant.orElse(null);
    }

    public Student updateCvByMatricule(String matricule, File cv) {
        //FIXME: Possible NullPointerException.
        Student student = getStudentByMatricule(matricule);
        student.setCvList(List.of(cv));
        return student;
    }

    Student getStudentByEmail(String email) {
        return studentRepository.findAllByEmailEqualsIgnoreCase(email).orElse(null);
    }

    @Transactional
    public List<StudentApplicationDto> getApplicationsByStudent(long id) {
        try {
            Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
            List<InternshipApplication> offersApplied = student.getInternshipApplications();

            if (offersApplied == null)
                return new ArrayList<>();

            return offersApplied.stream().map(
                    (offerApplied) -> {
                        StudentApplicationDto dto = new StudentApplicationDto();

                        InternshipOfferDto offerDto = new InternshipOfferDto(offerApplied.getInternshipOffer());
                        offerDto.setInternshipApplicationIds(null);

                        List<NewFileTransferDto> newFileTransferDtos = offerApplied.getFiles().stream().map(NewFileTransferDto::new).toList();

                        dto.setAppliedOffer(offerDto);
                        dto.setAppliedFiles(newFileTransferDtos);

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
