package com.sap.ose.projetose.service.auth;

import com.sap.ose.projetose.config.JwtService;
import com.sap.ose.projetose.controller.auth.AuthenticationRequest;
import com.sap.ose.projetose.controller.auth.AuthenticationResponse;
import com.sap.ose.projetose.dto.EmployeurAuthDto;
import com.sap.ose.projetose.exception.ErrorResponse;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.service.ProgrammeService;
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
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(EmployeurAuthDto employeurAuthDto) {

        Programme programme = programmeService.getProgrammeById(employeurAuthDto.getProgramme_id()).fromDto();

        Employeur employeur = new Employeur();

        employeur.setNom(employeurAuthDto.getNom());
        employeur.setPrenom(employeurAuthDto.getPrenom());
        employeur.setEmail(employeurAuthDto.getEmail());
        employeur.setPhone(employeurAuthDto.getPhone());
        employeur.setRole(Role.EMPLOYEUR);
        employeur.setPassword(passwordEncoder.encode(employeurAuthDto.getPassword()));
        employeur.setEntreprise(employeurAuthDto.getEntreprise());
        employeur.setProgramme(programme);

        employeurRepository.save(employeur);

        var jwtToken = jwtService.generateToken(employeur);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            Employeur employeur = employeurRepository.findByEmail(request.getEmail()).orElseThrow();

            var jwtToken = jwtService.generateToken(employeur);
            return AuthenticationResponse.builder().token(jwtToken).build();
        } catch (AuthenticationException e) {
            return AuthenticationResponse.builder()
                    .token(null)
                    .build();
        }
    }



}
