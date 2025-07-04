package edu.corhuila.unitrack.application.dto.response;

public record StudentResponse(
    Long id,
    String firstName,
    String lastName,
    String email,
    String studentCode,
    String program,
    Integer semester,
    Double averageGrade
) {}