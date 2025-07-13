package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.service.StudentGradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student-grades")
@CrossOrigin(origins = "*")
public class StudentGradeController {
    private final StudentGradeService studentGradeService;

    public StudentGradeController(StudentGradeService studentGradeService) {
        this.studentGradeService = studentGradeService;
    }

    @GetMapping("/student/{studentId}/subject/{subjectId}")
    public ResponseEntity<Double> getFinalGradeBySubject(
            @PathVariable Long studentId,
            @PathVariable Long subjectId) {
        var grade = studentGradeService.calculateFinalGrade(studentId, subjectId);
        return ResponseEntity.ok(grade);
    }

    @GetMapping("/student/{studentId}/subject/{subjectId}/cut/{cutId}")
    public ResponseEntity<Double> getFinalGradeBySubjectAndCut(
            @PathVariable Long studentId,
            @PathVariable Long subjectId,
            @PathVariable Long cutId) {
        var grade = studentGradeService.calculateFinalGradeByCut(studentId, subjectId, cutId);
        return ResponseEntity.ok(grade);
    }
}