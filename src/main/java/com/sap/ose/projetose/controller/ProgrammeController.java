package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.ProgramDto;
import com.sap.ose.projetose.service.ProgrammeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programme")
public class ProgrammeController {

    public final ProgrammeService programmeService;

    public ProgrammeController(ProgrammeService programmeService) {
        this.programmeService = programmeService;
    }


    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ProgramDto> saveProgramme(@RequestBody ProgramDto programDTO) {
        return programmeService.saveProgramme(programDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/programmes")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<ProgramDto> getProgrammes() {
        return programmeService.getProgrammes();
    }


}
