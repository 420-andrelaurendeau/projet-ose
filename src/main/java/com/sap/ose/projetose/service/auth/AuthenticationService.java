package com.sap.ose.projetose.service.auth;

import com.sap.ose.projetose.controller.auth.AuthenticationRequest;
import com.sap.ose.projetose.controller.auth.AuthenticationResponse;
import com.sap.ose.projetose.controller.auth.RegisterRequest;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request) {
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
