package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.service.OseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
public class EtudiantController {


    private final OseService oseService;
     final Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    public EtudiantController(OseService oseService) {
        this.oseService = oseService;
    }


    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Etudiant> saveEtudiant(@RequestBody Etudiant etudiant) {
        return oseService.saveEtudiant(etudiant).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public EtudiantDto getEtudiant(@PathVariable Long id) {
        return oseService.getEtudiantById(id);
    }

    @GetMapping("/etudiants")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<EtudiantDto>> getEtudiants() {
        logger.info("getEtudiants");
        return ResponseEntity.ok().body(oseService.getAllEtudiants());
    }


}
