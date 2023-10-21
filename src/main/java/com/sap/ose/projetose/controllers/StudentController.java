package com.sap.ose.projetose.controllers;

import com.sap.ose.projetose.dtos.StudentDto;
import com.sap.ose.projetose.dtos.StudentApplicationsDto;
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

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    @PostMapping({"/register", "/update"})
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public StudentDto getStudent(@PathVariable Long id) {
        return studentService.getStudentDTOById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getStudents() {
        logger.info("get students");
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @PostMapping("/{id}/cv")
    public ResponseEntity<Student> addCv(@PathVariable String id, @RequestBody String cv) {
        // FIXME: Properly implement this method.
        logger.info("add cv to " + matricule );
        Student student = studentService.updateCvByMatricule(matricule, null);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("{id}/applications")
    public ResponseEntity<List<StudentApplicationsDto>> getApplicationsByStudent(@PathVariable long id) {
        return ResponseEntity.ok().body(studentService.getApplicationsByStudent(id));
    }
}
