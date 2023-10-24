package com.sap.ose.projetose.controllers;

import com.sap.ose.projetose.dtos.StudyProgramDto;
import com.sap.ose.projetose.services.StudyProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/programme")
@RequiredArgsConstructor
public class StudyProgramController {
    public final StudyProgramService studyProgramService;

    @PostMapping( "/new")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<StudyProgramDto> createStudyProgram(@RequestBody StudyProgramDto studyProgramDTO) {
        //FIXME: make sure this only returns a valid response, Global Exception Handler will handle the rest.
        // make a new NewStudyProgramDto without ID and with validation for this one.
        // Checkout <see EmployerController.java> for an example.
        return studyProgramService
                .saveStudyProgram(studyProgramDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<StudyProgramDto> getStudyPrograms() {
        return studyProgramService.getAllStudyPrograms();
    }
}
