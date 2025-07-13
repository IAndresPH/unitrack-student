package edu.corhuila.unitrack.application.dto.response;

public record StudentSubjectResponse(
    Long studentId,
    Long subjectId,
    Double finalGrade
) {}