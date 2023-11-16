package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.EtudiantInscriptionDto;
import com.sap.ose.projetose.dto.FileDtoAll;
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
@RequestMapping("/api/student")
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
    @PreAuthorize("hasAuthority('internshipmanager')")
    public ResponseEntity<List<EtudiantDto>> getEtudiants() {
        logger.info("getEtudiants");
        return ResponseEntity.ok().body(etudiantService.getEtudiants());
    }

    @PostMapping("/addCv/{matricule}")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public ResponseEntity<EtudiantDto> addCv(@PathVariable String matricule, @RequestBody File cv){
        logger.info("add cv to " + matricule );
        EtudiantDto etudiantDto = etudiantService.updateCVByMatricule(matricule, cv);
        return ResponseEntity.ok().body(etudiantDto);
    }

    @GetMapping("{id}/offersApplied")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public ResponseEntity<List<StudentAppliedOffersDto>> getOffersApplied(@PathVariable long id) {

        return ResponseEntity.ok().body(etudiantService.getOffersAppliedByEtudiant(id));
    }

    @GetMapping("{id}/cvs")
    @PreAuthorize("hasAuthority('student')")
    public ResponseEntity<List<FileDtoAll>> getEtudiantsWithCv(@PathVariable long id) {
        logger.info("getEtudiantsWithCv");
        return ResponseEntity.ok().body(etudiantService.getAllCvfromStudentById(id));
    }

    @PostMapping("{id}/cv/{cvId}/setDefault")
    @PreAuthorize("hasAuthority('student')")
    public ResponseEntity<FileDtoAll> setDefaultCv(@PathVariable long id, @PathVariable long cvId) {
        logger.info("setDefaultCv");
        return ResponseEntity.ok().body(etudiantService.setDefaultCv(id, cvId));
    }

    @GetMapping("{id}/defaultCv")
    @PreAuthorize("hasAuthority('student')")
    public ResponseEntity<FileDtoAll> getDefaultCv(@PathVariable long id) {
        logger.info("getDefaultCv");
        return ResponseEntity.ok().body(etudiantService.getDefaultCv(id));
    }
}
