package com.sap.ose.projetose.controllers;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.dtos.InternshipApplicationDto;
import com.sap.ose.projetose.dtos.StudentDto;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/register")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        //FIXME: Use DTO without id in it.
        return studentService.createStudent(student).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getStudents() {
        //FIXME: is this log necessary?
        logger.info("get students");
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public StudentDto getStudent(@PathVariable @UserExists Long id) {
        return studentService.getStudentDTOById(id);
    }

    @PostMapping("/{id}/cv")
    public ResponseEntity<Student> addCv(@PathVariable @UserExists Long id, @RequestBody @FileExists Long cv) {
        // FIXME: Properly implement this method to use StudentId instead of matricule. It should return a StudentDto.
        logger.info("add cv to " + matricule);
        Student student = studentService.updateCvByMatricule(matricule, null);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("{id}/applications")
    public ResponseEntity<List<InternshipApplicationDto>> getApplicationsByStudent(@PathVariable long id) {
        //FIXME: Properly implement this method to return InternshipApplicationDto
        return ResponseEntity.ok().body(studentService.getApplicationsByStudent(id));
    }
}
