package com.sap.ose.projetose.controllers;

import com.sap.ose.projetose.dtos.NewStudyProgramDto;
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
    public ResponseEntity<StudyProgramDto> createStudyProgram(@RequestBody NewStudyProgramDto studyProgramDTO) {
        return ResponseEntity.ok(studyProgramService.createStudyProgram(studyProgramDTO));
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<StudyProgramDto> getStudyPrograms() {
        return studyProgramService.getAllStudyPrograms();
    }
}
