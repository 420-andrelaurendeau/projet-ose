package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.service.EmployerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employeur")
public class EmployerController {

    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Employer> saveEmployeur(@RequestBody Employer employer){
        return employerService.saveEmployeur(employer).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
