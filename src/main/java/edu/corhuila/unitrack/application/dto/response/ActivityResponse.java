package edu.corhuila.unitrack.application.dto.response;

public record ActivityResponse(
    Long id,
    String name,
    Double percentage,
    Double grade,
    Long cutId,
    Long subjectId
) {}