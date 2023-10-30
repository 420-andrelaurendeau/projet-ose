package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.service.FileService;
import com.sap.ose.projetose.service.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantService etudiantService;
    private final FileService fileService;
    Logger logger = LoggerFactory.getLogger(EtudiantController.class);

    @PostMapping("/register")
    public ResponseEntity<Etudiant> createStudent(@RequestBody Etudiant etudiant) {
        //FIXME: Use DTO without id in it.
        return etudiantService.createStudent(etudiant).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<EtudiantDto>> getStudents() {
        //FIXME: is this log necessary?
        logger.info("get students");
        return ResponseEntity.ok().body(etudiantService.getStudents());
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public EtudiantDto getStudent(@PathVariable @UserExists Long id) {
        return etudiantService.getStudentDTOById(id);
    }

    @PostMapping("/{id}/cv")
    public ResponseEntity<Etudiant> addCv(@PathVariable @UserExists Long id, @RequestBody @FileExists Long cv) {
        File cvFile = fileService.getFileById(cv);
        Etudiant etudiant = etudiantService.updateCvById(id, cvFile);
        return ResponseEntity.ok().body(etudiant);
    }

    @GetMapping("{id}/applications")
    public ResponseEntity<List<InternshipCandidatesDto>> getApplicationsByStudent(@PathVariable long id) {
        //FIXME: Properly implement this method to return InternshipCandidatesDto
        return ResponseEntity.ok().body(etudiantService.getApplicationsByStudent(id));
    }
}
