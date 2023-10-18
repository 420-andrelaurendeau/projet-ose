package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.dto.EmployerDto;
import com.sap.ose.projetose.dto.newEmployerDto;
import com.sap.ose.projetose.service.EmployerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
@Validated
public class EmployerController {
    private final EmployerService employerService;
    @PostMapping("/new")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EmployerDto> newEmployer(@Valid @RequestBody newEmployerDto employer){
        return employerService.newEmployer(employer).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EmployerDto> getEmployerById(@UserExists @PathVariable Long id){
        return ResponseEntity.ok(new EmployerDto(employerService.findById(id)));
    }
}
