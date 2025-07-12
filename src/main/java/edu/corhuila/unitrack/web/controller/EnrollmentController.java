package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.dto.request.EnrollmentRequest;
import edu.corhuila.unitrack.application.dto.response.EnrollmentResponse;
import edu.corhuila.unitrack.application.service.EnrollmentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public EnrollmentResponse create(@RequestBody EnrollmentRequest request) {
        return enrollmentService.create(request);
    }

    @GetMapping("/student/{studentId}")
    public List<EnrollmentResponse> getAllByStudentId(@PathVariable Long studentId) {
        return enrollmentService.getAllByStudentId(studentId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        enrollmentService.delete(id);
    }
}