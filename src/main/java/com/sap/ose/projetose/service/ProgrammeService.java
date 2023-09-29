package com.sap.ose.projetose.service;

import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgrammeService {

    ProgrammeRepository programmeRepository;

    @Autowired
    public ProgrammeService(ProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    }

    public Optional<Programme> getProgrammeById(int id) {
        return programmeRepository.findById(id);
    }
}
