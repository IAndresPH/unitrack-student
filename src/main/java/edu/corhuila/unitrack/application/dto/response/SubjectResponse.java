package edu.corhuila.unitrack.application.dto.response;

public record SubjectResponse(
    Long id,
    String name,
    Integer credit,
    Double finalGrade,
    Long studentId
) {}