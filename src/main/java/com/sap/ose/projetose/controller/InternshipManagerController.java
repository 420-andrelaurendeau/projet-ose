package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.StudentAppliedOffersDto;
import com.sap.ose.projetose.service.InternshipmanagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/management/")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InternshipManagerController {
    private final InternshipmanagerService internshipmanagerService;

    @GetMapping("pending_cvs/{id}")
    public ResponseEntity<List<FileDto>> getOffersApplied(@PathVariable long id) {
        return ResponseEntity.ok().body(internshipmanagerService.getPendingCVsByDepartment(id));
    }

    @PostMapping("accept_cv/{id}")
    public ResponseEntity<?> acceptCv(@PathVariable long id) {
        internshipmanagerService.acceptCV(id);
        return ResponseEntity.ok().build();
    }
}