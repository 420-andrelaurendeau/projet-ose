package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EtudiantDTO;
import com.sap.ose.projetose.service.OseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etudiantApi")
public class EtudiantController {

    private final OseService oseService;

    public EtudiantController(OseService oseService) {
        this.oseService = oseService;
    }

    @PostMapping("/saveEtudiant")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EtudiantDTO> saveEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        return oseService.saveEtudiant(etudiantDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
