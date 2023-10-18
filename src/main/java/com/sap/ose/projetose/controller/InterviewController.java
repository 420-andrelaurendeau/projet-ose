package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InterviewDTO;
import com.sap.ose.projetose.dto.InterviewRequestInDto;
import com.sap.ose.projetose.modeles.Interview;
import com.sap.ose.projetose.service.InterviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
@CrossOrigin(origins = "http://localhost:3000")
public class InterviewController {

    Logger logger = LoggerFactory.getLogger(ReactOseController.class);
    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/save")
    public ResponseEntity<InterviewDTO> saveInterview(@RequestBody InterviewRequestInDto interviewRequestInDto){
        logger.info("Interview request received");
        System.out.println(interviewRequestInDto);
        return interviewService.saveInterview(interviewRequestInDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
