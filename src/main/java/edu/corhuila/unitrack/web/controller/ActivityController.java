package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.dto.request.ActivityRequest;
import edu.corhuila.unitrack.application.dto.response.ActivityResponse;
import edu.corhuila.unitrack.application.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "*")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ActivityResponse> create(@RequestBody ActivityRequest request) {
        return ResponseEntity.ok(activityService.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}