package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.dto.request.CutRequest;
import edu.corhuila.unitrack.application.dto.response.CutResponse;
import edu.corhuila.unitrack.application.service.CutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cuts")
@CrossOrigin(origins = "*")
public class CutController {
    private final CutService cutService;

    public CutController(CutService cutService) {
        this.cutService = cutService;
    }

    @PostMapping
    public ResponseEntity<CutResponse> create(@RequestBody CutRequest request) {
        return ResponseEntity.ok(cutService.create(request));
    }
}