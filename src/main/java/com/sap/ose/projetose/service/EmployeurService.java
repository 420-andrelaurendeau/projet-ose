package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EmployeurInscriptionDtoInscription;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EmployeurRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeurService {
    private final EmployeurRepository employeurRepository;
    private final ProgrammeService programmeService;

    @Transactional
    public Employeur findById(long id) {
        return employeurRepository.findById(id).orElseThrow();
    }

    @Transactional
    public EmployeurDto createEmployer(EmployeurInscriptionDtoInscription employerDto) {
        Employeur employeur = new Employeur();
        Set<Programme> Programme = programmeService.findProgramsById(employerDto.getStudyProgramId());

        employeur.setLastName(employerDto.getLastName());
        employeur.setFirstName(employerDto.getFirstName());
        employeur.setPhoneNumber(employerDto.getPhoneNumber());
        employeur.setEmail(employerDto.getEmail());
        employeur.setPassword(employerDto.getPassword());
        employeur.setProgramme(Programme);
        employeur.setEnterprise(employerDto.getEnterprise());

        return new EmployeurDto(employeurRepository.save(employeur));
    }
}
