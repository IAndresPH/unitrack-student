package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.User;

public interface IJwtProvider {
    String generateToken(User user);
    String extractUsername(String token);
    Long extractUserId(String token);
    boolean isValidToken(String token, User user);
}