package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.service.ProgrammeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('internshipmanager')")
    public ResponseEntity<ProgrammeDto> saveProgramme(@RequestBody ProgrammeDto programmeDTO) {
        return programmeService.saveProgramme(programmeDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/programmes")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<ProgrammeDto> getProgrammes() {
        return programmeService.getProgrammes();
    }


}
