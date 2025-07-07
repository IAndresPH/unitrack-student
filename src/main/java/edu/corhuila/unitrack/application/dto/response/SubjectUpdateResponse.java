package edu.corhuila.unitrack.application.dto.response;

public record SubjectUpdateResponse(
    Long id,
    String name,
    Integer credit
) {}