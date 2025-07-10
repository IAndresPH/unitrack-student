package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.dto.request.CutRequest;
import edu.corhuila.unitrack.application.dto.response.CutResponse;
import edu.corhuila.unitrack.application.service.CutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/cuts")
@CrossOrigin(origins = "*")
public class CutController {
    private final CutService cutService;

    public CutController(CutService cutService) {
        this.cutService = cutService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CutResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cutService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CutResponse> create(@RequestBody CutRequest request) {
        return ResponseEntity.ok(cutService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CutResponse> update(@PathVariable Long id, @RequestBody CutRequest request) {
        return ResponseEntity.ok(cutService.update(id, request));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<CutResponse>> getAllBySubjectId(@PathVariable Long subjectId) {
        return ResponseEntity.ok(cutService.getAllBySubjectId(subjectId));
    }

    @GetMapping("/subject/{subjectId}/final-grade")
    public ResponseEntity<Double> calculateFinalGradeBySubjectId(@PathVariable Long subjectId) {
        return ResponseEntity.ok(cutService.calculateFinalGradeBySubjectId(subjectId));
    }
}