package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.service.StageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stage")
@CrossOrigin(origins = "http://localhost:3000")
public class StageController {

    private final StageService stageService;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);


    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public ResponseEntity<StageDto> saveStage(@RequestBody StageDto stageDto) {
        logger.info("Interview request received");
        return stageService.save(stageDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pending/{studentId}")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public ResponseEntity<List<StageDto>> getStagePendingStudent(@PathVariable long studentId) {
        logger.info("Stage Pending request received");
        return Optional.of(stageService.getStageStudentPending(studentId)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PreAuthorize("hasAuthority('internshipmanager')")
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getStageCount() {
        logger.info("Stage count request received");
        return Optional.of(stageService.getCountByState()).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employeurStage/{id}")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('employeur')")
    public ResponseEntity<Page<InternshipAgreementDto>> getEmployeurStage(
            @PathVariable long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "desc") String sortDirection,
            @RequestParam(required = false) String state
    ) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Page<InternshipAgreementDto> internOfferDtos = stageService.getSortedByPageOfEmployeur(page, size, sort, state, id);

        System.out.println(internOfferDtos.get().collect(Collectors.toList()));
        return new ResponseEntity<>(internOfferDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('internshipmanager')")
    @GetMapping("/stages")
    public ResponseEntity<Page<InternshipAgreementDto>> getOffers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "desc") String sortDirection,
            @RequestParam(required = false) String state) {

        System.out.println(state);
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Page<InternshipAgreementDto> internOfferDtos = stageService.getSortedByPage(page, size, sort, state);

        System.out.println(internOfferDtos.get().collect(Collectors.toList()));
        return new ResponseEntity<>(internOfferDtos, HttpStatus.OK);
    }

    @PostMapping("/acceptedStudent")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public ResponseEntity<Boolean> getStudentAccepted(@RequestBody StageDto stageDto) {
        logger.info("Interview accept request received");
        return stageService.setStudentAccepted(stageDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/declinedStudent")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public ResponseEntity<Boolean> getStudentDeclined(@RequestBody StageDto stageDto) {
        logger.info("Interview accept request received");
        return stageService.setStudentDeclined(stageDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
