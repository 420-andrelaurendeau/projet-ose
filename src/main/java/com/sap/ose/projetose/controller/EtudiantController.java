package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.StudentAppliedOffersDto;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.service.EtudiantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.service.EtudiantService;



import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
@CrossOrigin(origins = "http://localhost:3000")
public class EtudiantController {

    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Etudiant> saveEtudiant(@RequestBody Etudiant etudiant) {

        return etudiantService.saveEtudiant(etudiant).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public EtudiantDto getEtudiant(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id);
    }

    @GetMapping("/etudiants")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<EtudiantDto>> getEtudiants() {
        logger.info("getEtudiants");
        return ResponseEntity.ok().body(etudiantService.getEtudiants());
    }

    @PostMapping("/addCv/{matricule}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('STUDENT')")
    public ResponseEntity<EtudiantDto> addCv(@PathVariable String matricule, @RequestBody File cv){
        logger.info("add cv to " + matricule );
        EtudiantDto etudiantDto = etudiantService.updateCVByMatricule(matricule, cv);
        return ResponseEntity.ok().body(etudiantDto);
    }

    @GetMapping("{id}/offersApplied")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('STUDENT')")
    public ResponseEntity<List<StudentAppliedOffersDto>> getOffersApplied(@PathVariable long id) {

        return ResponseEntity.ok().body(etudiantService.getOffersAppliedByEtudiant(id));
    }
}
