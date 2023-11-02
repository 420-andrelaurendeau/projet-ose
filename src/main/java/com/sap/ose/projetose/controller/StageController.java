package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InterviewDTO;
import com.sap.ose.projetose.dto.InterviewRequestInDto;
import com.sap.ose.projetose.dto.StageDto;
import com.sap.ose.projetose.service.StageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<StageDto> saveStage(@RequestBody StageDto stageDto){
        logger.info("Interview request received");
        return stageService.save(stageDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pending/{studentId}")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public ResponseEntity<List<StageDto>> getStagePendingStudent(@PathVariable long studentId){
        logger.info("Stage Pending request received");
        return Optional.of(stageService.getStageStudentPending(studentId)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/acceptedStudent")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public ResponseEntity<Boolean> getStudentAccepted(@RequestBody StageDto stageDto){
        logger.info("Interview accept request received");
        return stageService.setStudentAccepted(stageDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/declinedStudent")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public ResponseEntity<Boolean> getStudentDeclined(@RequestBody StageDto stageDto){
        logger.info("Interview accept request received");
        return stageService.setStudentDeclined(stageDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
