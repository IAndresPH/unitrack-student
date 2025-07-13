package edu.corhuila.unitrack.application.dto.response;

import java.util.List;

public record EnrollmentPostResponse(
    Long id,
    Long studentId,
    List<Long> subjectIds,
    Integer semester
) {}