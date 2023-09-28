package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.service.OseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employeur")
public class EmployeurController {

    private final OseService oseService;

    public EmployeurController(OseService oseService) {
        this.oseService = oseService;
    }

    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Employeur> saveEmployeur(@RequestBody Employeur employeur) {
        return oseService.saveEmployeur(employeur).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
