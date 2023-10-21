package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.StudyProgramDto;
import com.sap.ose.projetose.exceptions.DatabaseException;
import com.sap.ose.projetose.exceptions.ProgramNotFoundException;
import com.sap.ose.projetose.exceptions.ServiceException;
import com.sap.ose.projetose.models.StudyProgram;
import com.sap.ose.projetose.repositories.StudyProgramRepository;
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
public class StudyProgramService {

    private final StudyProgramRepository studyProgramRepository;
    private final Logger logger = LoggerFactory.getLogger(StudyProgramService.class);

    StudyProgram findProgramById(long id) {
        try {
            return studyProgramRepository.findById(id).orElseThrow(ProgramNotFoundException::new);

        } catch (ProgramNotFoundException e) {
            logger.error("Programme non trouvé avec l'Id" + id);
            throw e;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès aux données lors de la récupération du programme avec l'ID :" + id, e);
            throw new DatabaseException("Erreur lors de la récupération du programme") {
            };
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération du programme avec l'ID :" + id, e);
            throw new ServiceException("Erreur lors de la récupération du programme");
        }
    }

    @Transactional
    public Optional<StudyProgramDto> saveStudyProgram(StudyProgramDto studyProgramDTO) {
        return Optional.of(new StudyProgramDto(studyProgramRepository.save(studyProgramDTO)));
    }

    public List<StudyProgramDto> getAllStudyPrograms() {
        List<StudyProgramDto> dtos = new ArrayList<>();
        for (StudyProgram studyProgram : studyProgramRepository.findAll()) {
            dtos.add(new StudyProgramDto(studyProgram));
        }
        return dtos;
    }
}
