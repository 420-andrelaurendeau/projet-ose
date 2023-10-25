package com.sap.ose.projetose.controller.auth;

import com.sap.ose.projetose.dto.auth.EmployeurAuthDto;
import com.sap.ose.projetose.dto.auth.EtudiantAuthDto;
import com.sap.ose.projetose.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/employeur")
    public ResponseEntity<AuthenticationResponse> registerEmployeur(@RequestBody EmployeurAuthDto employeurAuthDto){
        return ResponseEntity.ok(authenticationService.registerEmployeur(employeurAuthDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register/etudiant")
    public ResponseEntity<AuthenticationResponse> registerEtudiant(@RequestBody EtudiantAuthDto etudiantAuthDto){

        return ResponseEntity.ok(authenticationService.registerEtudiant(etudiantAuthDto));
    }
}
