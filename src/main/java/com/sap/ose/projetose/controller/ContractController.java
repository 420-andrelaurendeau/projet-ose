package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
        System.out.println(contractDto);
        return new ResponseEntity<>(contractService.saveContractEmployerDto(contractDto), HttpStatus.CREATED);
    }

    @GetMapping("student/{id}/getAll")
    @PreAuthorize("hasAuthority('student')")
    public ResponseEntity<Page<ContractDto>> getStudentContract(@PathVariable long id,
                                                                @RequestParam(required = false, defaultValue = "0") int page,
                                                                @RequestParam(required = false, defaultValue = "10") int size,
                                                                @RequestParam(required = false, defaultValue = "id") String sortField,
                                                                @RequestParam(required = false, defaultValue = "desc") String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return new ResponseEntity<>(contractService.getAllByStudentID(id, page, size, sort), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('employeur') OR hasAuthority('student')")
    public ResponseEntity<ContractDto> getContract(@PathVariable long id) {
        return new ResponseEntity<>(contractService.getById(id), HttpStatus.OK);
    }

}
