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
    public ResponseEntity<StageDto> saveInterview(@RequestBody StageDto stageDto){
        logger.info("Interview request received");
        return stageService.save(stageDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/Stage/{studentId}")
    public ResponseEntity<List<StageDto>> getStagePendingStudent(@RequestBody long studentId){
        logger.info("Stage Pending request received");
        return Optional.of(stageService.getStageStudentPending(studentId)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PreAuthorize("hasAuthority('internshipmanager')")
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getStageCount(){
        logger.info("Stage count request received");
        return Optional.of(stageService.getCountByState()).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
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
        Page<InternshipAgreementDto> internOfferDtos = stageService.getSortedByPage(page, size,sort, state);

        System.out.println(internOfferDtos.get().collect(Collectors.toList()));
        return new ResponseEntity<>(internOfferDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('internshipmanager')")
    @GetMapping("/{id}")
    public ResponseEntity<InternshipAgreementDto> getStage(@PathVariable long id){
        logger.info("Stage request received");
        return new ResponseEntity<>(stageService.findById(id), HttpStatus.OK);
    }


}
