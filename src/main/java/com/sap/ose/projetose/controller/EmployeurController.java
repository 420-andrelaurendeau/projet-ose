package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EmployeurInscriptionDtoInscription;
import com.sap.ose.projetose.service.EmployeurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
public class EmployeurController {
    private final EmployeurService employeurService;

    @PostMapping("/new")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EmployeurDto> newEmployer(@Valid @RequestBody EmployeurInscriptionDtoInscription employer) {
        EmployeurDto newEmployeurDto = employeurService.createEmployer(employer);
        return new ResponseEntity<>(newEmployeurDto, HttpStatus.CREATED);
    }
}
