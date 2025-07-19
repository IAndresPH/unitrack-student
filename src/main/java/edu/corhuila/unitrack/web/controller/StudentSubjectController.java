package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import edu.corhuila.unitrack.application.service.StudentSubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/student-subjects")
@CrossOrigin(origins = "*")
public class StudentSubjectController {

    private final StudentSubjectService studentSubjectService;

    public StudentSubjectController(StudentSubjectService studentSubjectService) {
        this.studentSubjectService = studentSubjectService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SubjectResponse>> getSubjectsByStudent(@PathVariable Long studentId) {
        var subjects = studentSubjectService.getAllSubjectsWithFinalGradeByStudentId(studentId);
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/student/{studentId}/subject/{subjectId}/grade")
    public ResponseEntity<Double> getFinalGrade(@PathVariable Long studentId, @PathVariable Long subjectId) {
        var finalGrade = studentSubjectService.calculateFinalGradeByStudentAndSubject(studentId, subjectId);
        return ResponseEntity.ok(finalGrade);
    }
}