package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAuthority;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "http://localhost:3000")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("internshipmanager/save")
    @PreAuthorize("hasAuthority('internshipmanager')")
    public ResponseEntity<ContractDto> saveContract(@RequestBody ContractDto contractDto) {
        System.out.println(contractDto);
        return new ResponseEntity<>(contractService.saveContractDto(contractDto), HttpStatus.CREATED);
    }

    @PostMapping("student/save")
    @PreAuthorize("hasAuthority('student')")
    public ResponseEntity<ContractDto> saveStudentContract(@RequestBody ContractDto contractDto) {
        System.out.println(contractDto);
        return new ResponseEntity<>(contractService.saveContractStudentDto(contractDto), HttpStatus.CREATED);
    }


    @PostMapping("employer/save")
    @PreAuthorize("hasAuthority('employer')")
    public ResponseEntity<ContractDto> saveEmployerContract(@RequestBody ContractDto contractDto) {
        return new ResponseEntity<>(contractService.saveContractEmployerDto(contractDto), HttpStatus.CREATED);
    }

    @GetMapping("student/{id}/getAll")
    @PreAuthorize("hasAuthority('student')")
    public ResponseEntity<List<ContractDto>> getStudentContract(@PathVariable long id) {

        return new ResponseEntity<>(contractService.getAllByStudentID(id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('internshipmanager') || hasAuthority('student') || hasAuthority('employer')")
    public ResponseEntity<ContractDto> getContract(@PathVariable long id) {
        return new ResponseEntity<>(contractService.getById(id), HttpStatus.OK);
    }

}
