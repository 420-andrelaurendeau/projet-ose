package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.NewStudyProgramDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.service.ProgrammeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/studyProgram")
@RequiredArgsConstructor
public class ProgrammeController {
    public final ProgrammeService programmeService;

    @PostMapping( "/new")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ProgrammeDto> createStudyProgram(@RequestBody NewStudyProgramDto studyProgramDTO) {
        return ResponseEntity.ok(programmeService.createStudyProgram(studyProgramDTO));
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<ProgrammeDto> getStudyPrograms() {
        return programmeService.getAllStudyPrograms();
    }
}
