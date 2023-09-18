package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.service.OseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
public class EtudiantController {

    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    private final OseService oseService;

    public EtudiantController(OseService oseService) {
        this.oseService = oseService;
    }

    @GetMapping("/etudiants")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<EtudiantDto>> getEtudiants() {
        logger.info("getEtudiants");
        return ResponseEntity.ok().body(oseService.getAllEtudiants());
    }



}
