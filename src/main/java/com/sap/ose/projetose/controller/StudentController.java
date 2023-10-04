package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.StudentApplicationDto;
import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class StudentController {

    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    private final StudentService studentService;

    @PostMapping("/ajouter")
    public ResponseEntity<Student> saveEtudiant(@RequestBody Student etudiant) {
        return studentService.saveEtudiant(etudiant).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public StudentDto getEtudiant(@PathVariable Long id) {
        return studentService.getEtudiantById(id);
    }

    @GetMapping("/etudiants")
    public ResponseEntity<List<StudentDto>> getEtudiants() {
        logger.info("getEtudiants");
        return ResponseEntity.ok().body(studentService.getEtudiants());
    }

    @GetMapping("{id}/offersApplied")
    public ResponseEntity<List<StudentApplicationDto>> getOffersApplied(@PathVariable long id) {

        return ResponseEntity.ok().body(studentService.getOffersAppliedByEtudiant(id));
    }
}
