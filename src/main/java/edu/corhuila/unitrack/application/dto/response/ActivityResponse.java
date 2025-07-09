package edu.corhuila.unitrack.application.dto.response;

public record ActivityResponse(
    Long id,
    String name,
    Double percentage,
    Long cutId
) {}