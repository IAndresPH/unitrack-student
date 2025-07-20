package edu.corhuila.unitrack.application.dto.response;

public record AuthResponse(
    String accessToken,
    String refreshToken
) {}