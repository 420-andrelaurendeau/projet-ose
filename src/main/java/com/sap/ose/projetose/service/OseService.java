package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployerDto;
import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.repository.StudentRepository;
import com.sap.ose.projetose.dto.UserDto;
import com.sap.ose.projetose.repository.EmployerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OseService {

    private final StudentRepository studentRepository;

    private final EmployerRepository employerRepository;

    public OseService(StudentRepository studentRepository, EmployerRepository employerRepository) {
        this.studentRepository = studentRepository;

        this.employerRepository = employerRepository;
    }


    public List<UserDto> getAllUsers() {
        Optional<List<Employer>> employeurs = Optional.of(employerRepository.findAll());
        Optional<List<Student>> etudiants = Optional.of(studentRepository.findAll());
        List<StudentDto> studentDtos = new ArrayList<>();
        List<EmployerDto> employerDtos = new ArrayList<>();

        etudiants.get().forEach(etudiant -> {
            StudentDto studentDto = new StudentDto();
            studentDto.setNom(etudiant.getNom());
            studentDto.setPrenom(etudiant.getPrenom());
            studentDto.setEmail(etudiant.getEmail());
            studentDto.setPhone(etudiant.getPhone());
            studentDto.setMatricule(etudiant.getMatricule());
            studentDto.setCv(etudiant.getCv());
            studentDto.setProgrammeId(etudiant.getFormation().getId());
            studentDtos.add(studentDto);
        });

        employeurs.get().forEach(employeur -> {
            EmployerDto employerDto = new EmployerDto();
            employerDto.setNom(employeur.getNom());
            employerDto.setPrenom(employeur.getPrenom());
            employerDto.setPhone(employeur.getPhone());
            employerDto.setEmail(employeur.getEmail());
            employerDto.setEntreprise(employeur.getEntreprise());
            employerDtos.add(employerDto);
        });
        List<UserDto> utilisateurs = new ArrayList<>(studentDtos);
        utilisateurs.addAll(employerDtos);
        return utilisateurs;
    }




}
