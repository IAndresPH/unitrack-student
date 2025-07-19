package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.dto.request.StudentRequest;
import edu.corhuila.unitrack.application.dto.request.StudentUpdateRequest;
import edu.corhuila.unitrack.application.dto.response.StudentResponse;
import edu.corhuila.unitrack.application.dto.response.StudentUpdateResponse;
import edu.corhuila.unitrack.application.service.StudentService;
import edu.corhuila.unitrack.infrastructure.security.AuthenticatedUserProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public StudentController(StudentService studentService, AuthenticatedUserProvider authenticatedUserProvider) {
        this.studentService = studentService;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody StudentRequest request) {
        studentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<StudentResponse> getMyStudentInfo() {
        try {
            Long studentId = authenticatedUserProvider.getAuthenticatedStudentId();
            StudentResponse response = studentService.getById(studentId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentUpdateResponse> update(@PathVariable Long id, @Valid @RequestBody StudentUpdateRequest request) {
        StudentUpdateResponse response = studentService.update(id, request);
        return ResponseEntity.ok(response);
    }
}