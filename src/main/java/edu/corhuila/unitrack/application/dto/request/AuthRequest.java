package edu.corhuila.unitrack.application.dto.request;

public record AuthRequest(
    String usernameOrEmail,
    String password
) {}