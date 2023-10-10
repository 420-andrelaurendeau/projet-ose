package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employeur")
@RequiredArgsConstructor
public class EmployerController {
    private final EmployerService employerService;

    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Employer> saveEmployer(@RequestBody Employer employer){
        return employerService.saveEmployer(employer).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
