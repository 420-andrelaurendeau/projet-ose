package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.service.OseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programme")
public class ProgrammeController {

    public final OseService oseService;

    public ProgrammeController(OseService oseService) {
        this.oseService = oseService;
    }

    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ProgrammeDto> saveProgramme(@RequestBody ProgrammeDto programmeDTO) {
        return oseService.saveProgramme(programmeDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/programmes")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<ProgrammeDto> getProgrammes() {
        return oseService.getProgrammes();
    }


}
