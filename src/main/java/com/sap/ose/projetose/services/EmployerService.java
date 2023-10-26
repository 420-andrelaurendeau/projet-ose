package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.EmployerDto;
import com.sap.ose.projetose.dtos.NewEmployerDto;
import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.models.StudyProgram;
import com.sap.ose.projetose.repositories.EmployerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;
    private final StudyProgramService studyProgramService;

    @Transactional
    public Employer findById(long id) {
        return employerRepository.findById(id).orElseThrow();
    }

    @Transactional
    public EmployerDto createEmployer(NewEmployerDto employerDto) {
        Employer employer = new Employer();
        Set<StudyProgram> StudyProgram = studyProgramService.findProgramsById(employerDto.getStudyProgramId());

        employer.setLastName(employerDto.getLastName());
        employer.setFirstName(employerDto.getFirstName());
        employer.setPhoneNumber(employerDto.getPhoneNumber());
        employer.setEmail(employerDto.getEmail());
        employer.setPassword(employerDto.getPassword());
        employer.setStudyProgram(StudyProgram);
        employer.setEnterprise(employerDto.getEnterprise());

        return new EmployerDto(employerRepository.save(employer));
    }
}
