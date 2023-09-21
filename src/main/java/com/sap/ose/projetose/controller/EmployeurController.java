package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EmployeurDTO;
import com.sap.ose.projetose.service.OseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employeur")
public class EmployeurController {

    private final OseService oseService;

    public EmployeurController( OseService oseService) {
        this.oseService = oseService;
    }

    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EmployeurDTO> saveEmployeur(@RequestBody EmployeurDTO employeurDTO){
        return oseService.saveEmployeur(employeurDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
