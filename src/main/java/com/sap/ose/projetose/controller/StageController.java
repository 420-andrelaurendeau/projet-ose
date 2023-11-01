package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InterviewDTO;
import com.sap.ose.projetose.dto.InterviewRequestInDto;
import com.sap.ose.projetose.dto.StageDto;
import com.sap.ose.projetose.service.StageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StageDto> saveInterview(@RequestBody StageDto stageDto){
        logger.info("Interview request received");
        return stageService.save(stageDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/Stage/{studentId}")
    public ResponseEntity<List<StageDto>> getStagePendingStudent(@RequestBody long studentId){
        logger.info("Stage Pending request received");
        return Optional.of(stageService.getStageStudentPending(studentId)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
