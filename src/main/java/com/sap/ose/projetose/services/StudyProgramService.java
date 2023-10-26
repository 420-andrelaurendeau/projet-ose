package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.NewStudyProgramDto;
import com.sap.ose.projetose.dtos.StudyProgramDto;
import com.sap.ose.projetose.exceptions.ProgramNotFoundException;
import com.sap.ose.projetose.models.StudyProgram;
import com.sap.ose.projetose.repositories.StudyProgramRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudyProgramService {

    private final StudyProgramRepository studyProgramRepository;

    StudyProgram findProgramById(long id) {
        return studyProgramRepository.findById(id).orElseThrow(ProgramNotFoundException::new);
    }

    @Transactional
    public StudyProgramDto createStudyProgram(NewStudyProgramDto newStudyProgramDto) {
        var studyProgram = new StudyProgram();

        studyProgram.setNom(newStudyProgramDto.getName());
        studyProgram.setDescription(newStudyProgramDto.getDescription());

        return new StudyProgramDto(studyProgramRepository.save(studyProgram));
    }

    public List<StudyProgramDto> getAllStudyPrograms() {
        List<StudyProgramDto> dtos = new ArrayList<>();
        for (StudyProgram studyProgram : studyProgramRepository.findAll()) {
            dtos.add(new StudyProgramDto(studyProgram));
        }
        return dtos;
    }

    public Set<StudyProgram> findProgramsById(Set<Long> studyProgramId) {
        return new HashSet<>(studyProgramRepository.findAllById(studyProgramId));
    }
}
