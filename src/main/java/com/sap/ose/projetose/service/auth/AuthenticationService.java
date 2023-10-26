package com.sap.ose.projetose.service.auth;

import com.sap.ose.projetose.config.JwtService;
import com.sap.ose.projetose.controller.auth.AuthenticationRequest;
import com.sap.ose.projetose.controller.auth.AuthenticationResponse;
import com.sap.ose.projetose.dto.auth.EmployeurAuthDto;
import com.sap.ose.projetose.dto.auth.EtudiantAuthDto;
import com.sap.ose.projetose.dto.auth.InternshipmanagerAuthDto;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.service.ProgrammeService;
import com.sap.ose.projetose.service.UtilisateurService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ProgrammeService programmeService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeurRepository employeurRepository;
    private final EtudiantRepository etudiantRepository;
    private final InternshipmanagerRepository internshipmanagerRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UtilisateurService utilisateurService;

    public AuthenticationResponse registerEmployeur(EmployeurAuthDto employeurAuthDto) {

        Programme programme = programmeService.getProgrammeById(employeurAuthDto.getProgramme_id()).fromDto();

        Employeur employeur = new Employeur();

        employeur.setNom(employeurAuthDto.getNom());
        employeur.setPrenom(employeurAuthDto.getPrenom());
        employeur.setEmail(employeurAuthDto.getEmail());
        employeur.setPhone(employeurAuthDto.getPhone());
        employeur.setRole(Role.employer);
        employeur.setPassword(passwordEncoder.encode(employeurAuthDto.getPassword()));
        employeur.setEntreprise(employeurAuthDto.getEntreprise());
        employeur.setProgramme(programme);

        employeurRepository.save(employeur);

        var jwtToken = jwtService.generateToken(employeur);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse registerEmployeur(Employeur employeur) {
        employeur.setPassword(passwordEncoder.encode(employeur.getPassword()));
        employeur.setRole(Role.employer);
        employeurRepository.save(employeur);

        var jwtToken = jwtService.generateToken(employeur);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse registerEtudiant(EtudiantAuthDto etudiantAuthDto) {

        Programme programme = programmeService.getProgrammeById(etudiantAuthDto.getProgramme_id()).fromDto();

        Etudiant etudiant = new Etudiant();

        etudiant.setNom(etudiantAuthDto.getNom());
        etudiant.setPrenom(etudiantAuthDto.getPrenom());
        etudiant.setPhone(etudiantAuthDto.getPhone());
        etudiant.setEmail(etudiantAuthDto.getEmail());
        etudiant.setPassword(passwordEncoder.encode(etudiantAuthDto.getPassword()));
        etudiant.setMatricule(etudiantAuthDto.getMatricule());
        etudiant.setProgramme(programme);
        etudiant.setCv(null);
        etudiant.setInternshipsCandidate(null);
        etudiant.setRole(Role.student);

        etudiantRepository.save(etudiant);

        var jwtToken = jwtService.generateToken(etudiant);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse registerEtudiant(Etudiant etudiant) {

        etudiant.setPassword(passwordEncoder.encode(etudiant.getPassword()));
        etudiant.setRole(Role.student);
        etudiantRepository.save(etudiant);

        var jwtToken = jwtService.generateToken(etudiant);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse registerInternshipManager(InternshipmanagerAuthDto internshipmanagerAuthDto){
        Programme programme = programmeService.getProgrammeById(internshipmanagerAuthDto.getProgramme_id()).fromDto();

        Internshipmanager internshipmanager = new Internshipmanager();

        internshipmanager.setNom(internshipmanagerAuthDto.getNom());
        internshipmanager.setPrenom(internshipmanagerAuthDto.getPrenom());
        internshipmanager.setPhone(internshipmanagerAuthDto.getPhone());
        internshipmanager.setEmail(internshipmanagerAuthDto.getEmail());
        internshipmanager.setPassword(passwordEncoder.encode(internshipmanagerAuthDto.getPassword()));
        internshipmanager.setProgramme(programme);
        internshipmanager.setRole(Role.internshipmanager);

        internshipmanagerRepository.save(internshipmanager);

        var jwtToken = jwtService.generateToken(internshipmanager);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            Utilisateur utilisateur = utilisateurService.getUserByEmail(request.getEmail());

            var jwtToken = jwtService.generateToken(utilisateur);
            return AuthenticationResponse.builder().token(jwtToken).build();
        } catch (AuthenticationException e) {
            System.out.println("erreur dans authentication service avec l'authenticationManager ");
            return AuthenticationResponse.builder()
                    .token(null)
                    .build();
        }
    }

}
