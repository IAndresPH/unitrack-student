package edu.corhuila.unitrack.application.dto.request;

public record RegisterRequest(
    String username,
    String email,
    String password
) {}