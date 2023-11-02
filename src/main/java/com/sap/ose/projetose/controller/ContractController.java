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


        return new ResponseEntity<>(contractService.saveContract(contractDto), HttpStatus.CREATED);
    }


}
