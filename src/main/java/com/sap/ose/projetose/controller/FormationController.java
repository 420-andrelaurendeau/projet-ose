package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.FormationDto;
import com.sap.ose.projetose.service.FormationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programme")
@RequiredArgsConstructor
public class FormationController {
    public final FormationService formationService;

    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<FormationDto> saveProgramme(@RequestBody FormationDto formationDTO) {
        return formationService.saveProgramme(formationDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/programmes")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<FormationDto> getProgrammes() {
        return formationService.getProgrammes();
    }


}
