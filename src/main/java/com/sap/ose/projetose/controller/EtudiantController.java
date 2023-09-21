package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EtudiantDTO;
import com.sap.ose.projetose.service.OseService;
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


    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EtudiantDTO> saveEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        return oseService.saveEtudiant(etudiantDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/etudiants")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<EtudiantDTO> getEtudiants() {
        return oseService.getEtudiants();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public EtudiantDTO getEtudiant(@PathVariable int id) {
        return oseService.getEtudiantById(id);
    }

    @GetMapping("/etudiants")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<EtudiantDto>> getEtudiants() {
        logger.info("getEtudiants");
        return ResponseEntity.ok().body(oseService.getAllEtudiants());
    }




}
