package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipmanagerDto;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InternshipmanagerService {

    private final InternshipmanagerRepository internshipmanagerRepository;

    private final ProgrammeService programmeService;


    @Autowired
    public InternshipmanagerService(InternshipmanagerRepository internshipmanagerRepository, ProgrammeService programmeService) {
        this.internshipmanagerRepository = internshipmanagerRepository;
        this.programmeService = programmeService;
    }

    public InternshipmanagerDto getById(long id) {
        try {
            Internshipmanager internshipmanager = internshipmanagerRepository.findById(id).orElseThrow(() -> new NullPointerException("Internshipmanager non trouvé"));
            return new InternshipmanagerDto(
                    internshipmanager.getNom(),
                    internshipmanager.getPrenom(),
                    internshipmanager.getPhone(),
                    internshipmanager.getEmail(),
                    internshipmanager.getId(),
                    internshipmanager.getProgramme().getId()
            );
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    Internshipmanager findById(long id) {
        try {
            return  internshipmanagerRepository.findById(id).orElseThrow(() -> new NullPointerException("Internshipmanager non trouvé"));
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public void save(InternshipmanagerDto internshipmanagerDto){
        try {
            Programme program = programmeService.getProgrammeById(internshipmanagerDto.getProgrammeId()).orElseThrow(() -> new NullPointerException("Programme non trouvé"));

            Internshipmanager internshipmanager = internshipmanagerDto.fromDto();
            internshipmanager.setProgramme(program);

            internshipmanagerRepository.save(internshipmanager);


        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }
}
