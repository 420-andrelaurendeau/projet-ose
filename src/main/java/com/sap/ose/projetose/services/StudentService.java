package com.sap.ose.projetose.services;

import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.dtos.InternshipApplicationDto;
import com.sap.ose.projetose.dtos.StudentDto;
import com.sap.ose.projetose.exceptions.StudentNotFoundException;
import com.sap.ose.projetose.models.*;
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
    public Optional<Student> createStudent(Student student) {
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
            dtos.add(new StudentDto(student));
        }

        return dtos;
    }

    public StudentDto getStudentDTOById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(StudentDto::new).orElse(null);
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student updateCvById(Long id, File cv) {
        Student student = studentRepository.getReferenceById(id);
        student.setCvList(List.of(cv));
        return student;
    }

    Student getStudentByEmail(String email) {
        return studentRepository.findAllByEmailEqualsIgnoreCase(email).orElse(null);
    }

    @Transactional
    public List<InternshipApplicationDto> getApplicationsByStudent(long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        List<InternshipApplication> offersApplied = student.getInternshipApplications();

        if (offersApplied == null)
            return new ArrayList<>();

        return offersApplied.stream().map(
                (offerApplied) -> {
                    InternshipApplicationDto dto = new InternshipApplicationDto();

                    long offerDtoId = offerApplied.getInternshipOffer().getId();

                    List<Long> newFileTransferDtos = offerApplied.getFiles().stream().map(BaseModel::getId).toList();

                    dto.setInternshipOfferDtoId(offerDtoId);
                    dto.setFileTransferDtosId(newFileTransferDtos);

                    return dto;
                }).toList();
    }


}
