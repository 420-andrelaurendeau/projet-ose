package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "http://localhost:3000")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('internshipmanager')")
    public ResponseEntity<ContractDto> saveContract(@RequestBody ContractDto contractDto) {
        System.out.println(contractDto);
        return new ResponseEntity<>(contractService.saveContractDto(contractDto), HttpStatus.CREATED);
    }

    //TODO ajouter validation que employeur est le meme que le contract a chercher
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('employeur')")
    public ResponseEntity<ContractDto> getContract(@PathVariable long id) {
        return new ResponseEntity<>(contractService.getById(id), HttpStatus.OK);
    }

}
