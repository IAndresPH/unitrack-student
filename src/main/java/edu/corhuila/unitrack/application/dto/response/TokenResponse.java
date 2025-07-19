package edu.corhuila.unitrack.application.dto.response;

public record TokenResponse(
    Long id,
    String token,
    String tokenType,
    boolean isRevoked,
    boolean isExpired
) {}