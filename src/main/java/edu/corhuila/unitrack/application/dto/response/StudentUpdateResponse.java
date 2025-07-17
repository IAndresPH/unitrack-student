package edu.corhuila.unitrack.application.dto.response;

public record StudentUpdateResponse(
    Long id,
    String firstName,
    String lastName,
    String program
) {}