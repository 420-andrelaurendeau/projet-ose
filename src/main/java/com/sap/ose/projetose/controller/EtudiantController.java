package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.dto.StudentApplicationsDto;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.service.EtudiantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Student> saveEtudiant(@RequestBody Student student) {
        return etudiantService.saveEtudiant(student).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public StudentDto getEtudiant(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id);
    }

    @GetMapping("/etudiants")
    public ResponseEntity<List<StudentDto>> getEtudiants() {
        logger.info("getEtudiants");
        return ResponseEntity.ok().body(etudiantService.getEtudiants());
    }

    @PostMapping("/addCv/{matricule}")
    public ResponseEntity<Student> addCv(@PathVariable String matricule, @RequestBody String cv){
        logger.info("add cv to " + matricule );
        Student student = etudiantService.updateCVByMatricule(matricule, null);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("{id}/offersApplied")
    public ResponseEntity<List<StudentApplicationsDto>> getOffersApplied(@PathVariable long id) {

        return ResponseEntity.ok().body(etudiantService.getOffersAppliedByEtudiant(id));
    }
}
