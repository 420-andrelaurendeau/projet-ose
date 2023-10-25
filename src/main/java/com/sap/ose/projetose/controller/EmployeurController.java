package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.service.EmployeurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employeur")
public class EmployeurController {

    private final EmployeurService employeurService;

    public EmployeurController(EmployeurService employeurService) {
        this.employeurService = employeurService;
    }

    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Employeur> saveEmployeur(@RequestBody Employeur employeur){
        return employeurService.saveEmployeur(employeur).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
