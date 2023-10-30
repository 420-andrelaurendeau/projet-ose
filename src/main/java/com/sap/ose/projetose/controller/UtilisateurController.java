package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.UtilisateurDto;
import com.sap.ose.projetose.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<UtilisateurDto>> getAllUsers() {
        return ResponseEntity.ok().body(utilisateurService.getAllUsers());
    }

}
