package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeService {

    ProgrammeRepository programmeRepository;

    @Autowired
    public ProgrammeService(ProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    }

    public Optional<Programme> getProgrammeById(long id) {
        return programmeRepository.findById(id);
    }

    public ProgrammeDto saveProgramme(String nom, String description) {
        return new ProgrammeDto(programmeRepository.save(new Programme(nom, description)));
    }

    public Optional<ProgrammeDto> saveProgramme(ProgrammeDto programmeDTO) {
        return Optional.of(new ProgrammeDto(programmeRepository.save(programmeDTO.fromDto())));
    }

    public List<ProgrammeDto> getProgrammes() {
        List<ProgrammeDto> dtos = new ArrayList<>();
        for (Programme programme : programmeRepository.findAll()) {
            dtos.add(new ProgrammeDto(programme));
        }
        return dtos;
    }

    public ProgrammeDto getProgrammeById(Long id) {
        Optional<Programme> programme = programmeRepository.findById(id);
        return programme.map(value -> new ProgrammeDto(value.getId(), value.getNom(), value.getDescription())).orElse(null);
    }
}
