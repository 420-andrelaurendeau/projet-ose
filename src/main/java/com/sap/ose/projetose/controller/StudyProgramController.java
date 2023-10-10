package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.StudyProgramDto;
import com.sap.ose.projetose.service.StudyProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programme")
@RequiredArgsConstructor
public class StudyProgramController {
    public final StudyProgramService studyProgramService;

    @PostMapping("/ajouter")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<StudyProgramDto> saveStudyProgram(@RequestBody StudyProgramDto studyProgramDTO) {
        return studyProgramService
                .saveStudyProgram(studyProgramDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/programmes")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<StudyProgramDto> getStudyPrograms() {
        return studyProgramService.getAllStudyPrograms();
    }


}
