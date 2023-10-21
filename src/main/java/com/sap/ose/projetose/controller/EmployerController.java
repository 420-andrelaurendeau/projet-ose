package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EmployerDto;
import com.sap.ose.projetose.dto.NewEmployerDto;
import com.sap.ose.projetose.service.EmployerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
public class EmployerController {
    private final EmployerService employerService;
    @PostMapping("/new")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EmployerDto> newEmployer(@Valid @RequestBody NewEmployerDto employer){
        return employerService.createEmployer(employer).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
