package com.thilina.student_grades_api.controller;

import com.thilina.student_grades_api.dto.EnrollmentDto.GpaResponse;
import com.thilina.student_grades_api.dto.StudentDto;
import com.thilina.student_grades_api.dto.StudentDto.StudentResponse;
import com.thilina.student_grades_api.dto.StudentDto.StudentRequest;
import com.thilina.student_grades_api.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // POST
    @PostMapping
    public ResponseEntity<StudentDto.StudentResponse> create(
            @Valid @RequestBody StudentRequest req) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.createStudent(req));
    }

    // GET /api/studentd/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse>getOne(
            @PathVariable long id) {
        return ResponseEntity.ok(
                studentService.getStudentById(id)
        );
    }

    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/students/{id}/gpa
    @GetMapping("/{id}/gpa")
    public ResponseEntity<GpaResponse> getGpa(
            @PathVariable long id) {
        return ResponseEntity.ok(
                studentService.getStudentGpa(id)
        );
    }
}
