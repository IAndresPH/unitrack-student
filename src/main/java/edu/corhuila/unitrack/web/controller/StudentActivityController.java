package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.dto.request.StudentActivityRequest;
import edu.corhuila.unitrack.application.service.StudentActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student-activities")
@CrossOrigin(origins = "*")
public class StudentActivityController {

    private final StudentActivityService studentActivityService;

    public StudentActivityController(StudentActivityService studentActivityService) {
        this.studentActivityService = studentActivityService;
    }

    @PostMapping
    public ResponseEntity<Void> assignGradeToActivity(@RequestBody StudentActivityRequest request) {
        studentActivityService.assignGrade(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}/activity/{activityId}")
    public ResponseEntity<Double> getGrade(
            @PathVariable Long studentId,
            @PathVariable Long activityId
    ) {
        return ResponseEntity.ok(studentActivityService.getGrade(studentId, activityId));
    }
}