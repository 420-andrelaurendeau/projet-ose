package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.modeles.Interview;
import com.sap.ose.projetose.service.InterviewService;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<InterviewDTO> saveInterview(@RequestBody InterviewRequestInDto interviewRequestInDto) {
        logger.info("Interview request received");
        return interviewService.saveInterview(interviewRequestInDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/studentHasInterviewWithInternOffer")
    public ResponseEntity<Boolean> studentHasInterviewWithEmployer(@RequestBody studentHasInterviewWithInternOffer studentHasInterviewWithInternOffer) {
        logger.info("Interview studenthasinterviewwithemployer request received");
        boolean result = interviewService.studentHasInterviewWithEmployeur(studentHasInterviewWithInternOffer.studentId, studentHasInterviewWithInternOffer.internOfferId);
        return Optional.of(result).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<InterviewDTO>> getAllInterviews() {
        logger.info("Interview get request received");
        return Optional.of(interviewService.getAllInterviews()).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @GetMapping("/getByStudentId/{studentId}")
    public ResponseEntity<Page<InterviewDTO>> getInterviewsByStudentId(@PathVariable long studentId,
                                                                       @RequestParam(required = false, defaultValue = "0") int page,
                                                                       @RequestParam(required = false, defaultValue = "10") int size,
                                                                       @RequestParam(required = false, defaultValue = "id") String sortField,
                                                                       @RequestParam(required = false, defaultValue = "desc") String sortDirection
    ) {
        logger.info("Interview get request received");
        return Optional.of(interviewService.getInterviewsByStudentId(studentId, page, size,sortField, sortDirection)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @GetMapping("/getCountByStudentId/{studentId}")
    public ResponseEntity<Long> getInterviewsCountByStudentId(@PathVariable long studentId) {
        logger.info("Interview get request received");
        return interviewService.getInterviewsCountByStudentId(studentId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/studentAcceptsInterviewByStudentId/{studentId}/{InterviewId}")
    public ResponseEntity<Boolean> studentAcceptsInterviewByStudentId(@PathVariable long studentId, @PathVariable long InterviewId) {
        logger.info("Interview accept request received");
        return interviewService.studentAcceptsInterviewByStudentId(studentId, InterviewId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/getInterviewsByStudentIdAndInternOfferId")
    public ResponseEntity<InterviewDTO> getInterview(@RequestBody studentHasInterviewWithInternOffer studentHasInterviewWithInternOffer) {
        logger.info("Interview request received");
        return interviewService.getInterview(studentHasInterviewWithInternOffer.studentId, studentHasInterviewWithInternOffer.internOfferId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/studentDeclineInterviewByStudentId/{studentId}/{InterviewId}")
    public ResponseEntity<Boolean> studentRefuseInterviewByStudentId(@PathVariable long studentId, @PathVariable long InterviewId) {
        logger.info("Interview decline request received");
        return interviewService.studentDeclineInterviewByStudentId(studentId, InterviewId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}