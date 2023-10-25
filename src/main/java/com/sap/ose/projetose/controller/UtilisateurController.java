package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.UtilisateurAuthDto;
import com.sap.ose.projetose.dto.UtilisateurDto;
import com.sap.ose.projetose.dto.UtilisateurSignInDto;
import com.sap.ose.projetose.service.OseService;
import com.sap.ose.projetose.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);
    private final OseService oseService;
    private final UtilisateurService utilisateurService;

    public UtilisateurController(OseService oseService, UtilisateurService utilisateurService) {
        this.oseService = oseService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/utilisateurs")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<UtilisateurDto>> getAllUsers() {
        logger.info("getAllUsers");
        return ResponseEntity.ok().body(oseService.getAllUsers());
    }

    @GetMapping("/utilisateur/{email}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<UtilisateurSignInDto> getUserByEmail(@PathVariable String email) {
        logger.info("getUserByEmail");
        return ResponseEntity.ok().body(new UtilisateurSignInDto(utilisateurService.getUserByEmail(email)));
    }

}
