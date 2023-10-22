package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InterviewDTO;
import com.sap.ose.projetose.dto.InterviewRequestInDto;
import com.sap.ose.projetose.service.InterviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



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
        return interviewService.saveInterview(interviewRequestInDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/studentHasInterviewWithInternOffer")
    public ResponseEntity<Boolean> studentHasInterviewWithEmployer(@RequestBody studentHasInterviewWithInternOffer studentHasInterviewWithInternOffer ){
        logger.info("Interview studenthasinterviewwithemployer request received");
        boolean result = interviewService.studentHasInterviewWithEmployeur(studentHasInterviewWithInternOffer.studentId,studentHasInterviewWithInternOffer.internOfferId);
        return Optional.of(result).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

class studentHasInterviewWithInternOffer {
    long studentId,internOfferId;
    public studentHasInterviewWithInternOffer(long studentId, long internOfferId) {
        this.studentId = studentId;
        this.internOfferId = internOfferId;
    }
}