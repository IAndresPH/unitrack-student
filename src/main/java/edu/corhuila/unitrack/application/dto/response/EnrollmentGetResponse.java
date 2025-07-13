package edu.corhuila.unitrack.application.dto.response;

import java.util.List;

public record EnrollmentGetResponse(
    Long id,
    Long studentId,
    List<SubjectMiniResponse> subjectIds,
    Integer semester
) {}