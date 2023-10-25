package com.sap.ose.projetose.service.auth;

import com.sap.ose.projetose.config.JwtService;
import com.sap.ose.projetose.controller.auth.AuthenticationRequest;
import com.sap.ose.projetose.controller.auth.AuthenticationResponse;
import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.exception.ErrorResponse;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ProgrammeService programmeService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeurRepository employeurRepository;
    private final EtudiantRepository etudiantRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UtilisateurService utilisateurService;
    private final EtudiantService etudiantService;
    private final EmployeurService employeurService;
    private final Logger logger = LoggerFactory.getLogger(EmployeurService.class);


    public AuthenticationResponse registerEmployeur(Employeur employeurAuth, EmployeurAuthDto employeurAuthDto) {

        if (employeurAuthDto == null && employeurAuth != null) {
            try {
                Programme programme = programmeService.findById(employeurAuth.getProgramme().getId());
                employeurAuth.setProgramme(programme);
                employeurAuth.setRole(Role.EMPLOYEUR);
                employeurAuth.setPassword(passwordEncoder.encode(employeurAuth.getPassword()));
                employeurRepository.save(employeurAuth);
                var jwtToken = jwtService.generateToken(employeurAuth);
                return AuthenticationResponse.builder().token(jwtToken).build();
            } catch (DataAccessException e) {
                logger.info(e.getMessage());
                throw new DataAccessException("Error lors de la sauvegarde de l'employeur") {};
            }
        } else if (employeurAuthDto != null && employeurAuth == null) {
            try {
                Programme programme = programmeService.findById(employeurAuthDto.getProgramme_id());
                Employeur employeur = new Employeur();
                employeur.setNom(employeurAuthDto.getNom());
                employeur.setPrenom(employeurAuthDto.getPrenom());
                employeur.setPhone(employeurAuthDto.getPhone());
                employeur.setEmail(employeurAuthDto.getEmail());
                employeur.setPassword(passwordEncoder.encode(employeurAuthDto.getPassword()));
                employeur.setEntreprise(employeurAuthDto.getEntreprise());
                employeur.setProgramme(programme);
                employeur.setRole(Role.EMPLOYEUR);
                employeurRepository.save(employeur);
                var jwtToken = jwtService.generateToken(employeur);
                return AuthenticationResponse.builder().token(jwtToken).build();
            } catch (DataAccessException e) {
                logger.info(e.getMessage());
                throw new DataAccessException("Error lors de la sauvegarde de l'employeur") {};
            }
        } else {
            throw new DataAccessException("Error lors de la sauvegarde de l'employeur") {};
        }
    }

    public AuthenticationResponse registerEtudiant(EtudiantAuthDto etudiantAuthDto) {

        System.out.println(etudiantAuthDto.getProgramme_id());
        Programme programme = programmeService.getProgrammeById(etudiantAuthDto.getProgramme_id()).fromDto();

        System.out.println(programme.getId());
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
        etudiant.setRole(Role.ETUDIANT);

        etudiantService.saveEtudiant(etudiant);

        var jwtToken = jwtService.generateToken(etudiant);
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
            logger.info(e.getMessage());
            return AuthenticationResponse.builder()
                    .token(null)
                    .build();
        }
    }



}
