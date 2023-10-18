package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.StudyProgramDto;
import com.sap.ose.projetose.service.StudyProgramService;
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

    @PostMapping({"/update", "/new"})
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<StudyProgramDto> saveStudyProgram(@RequestBody StudyProgramDto studyProgramDTO) {
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
