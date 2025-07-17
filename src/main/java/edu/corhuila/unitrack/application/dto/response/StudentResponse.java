package edu.corhuila.unitrack.application.dto.response;

public record StudentResponse(
    Long id,
    String firstName,
    String lastName,
    String studentCode,
    Double averageGrade
) {}