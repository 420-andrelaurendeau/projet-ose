package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.service.EmployeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employeur")
public class EmployeurController {

    private final EmployeurService employeurService;

    @Autowired
    public EmployeurController(EmployeurService employeurService) {
        this.employeurService = employeurService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeurDto> getEmployeur(@PathVariable Long id) {
        return ResponseEntity.ok(employeurService.getEmployeurById(id));
    }


}
