package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.NewStudyProgramDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.exception.ProgramNotFoundException;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProgrammeService {

    private final ProgrammeRepository programmeRepository;

    Programme findProgramById(long id) {
        return programmeRepository.findById(id).orElseThrow(ProgramNotFoundException::new);
    }

    @Transactional
    public ProgrammeDto createStudyProgram(NewStudyProgramDto newStudyProgramDto) {
        var studyProgram = new Programme();

        studyProgram.setNom(newStudyProgramDto.getName());
        studyProgram.setDescription(newStudyProgramDto.getDescription());

        return new ProgrammeDto(programmeRepository.save(studyProgram));
    }

    public List<ProgrammeDto> getAllStudyPrograms() {
        List<ProgrammeDto> dtos = new ArrayList<>();
        for (Programme programme : programmeRepository.findAll()) {
            dtos.add(new ProgrammeDto(programme));
        }
        return dtos;
    }

    public Set<Programme> findProgramsById(Set<Long> studyProgramId) {
        return new HashSet<>(programmeRepository.findAllById(studyProgramId));
    }
}
