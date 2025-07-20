package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.User;

public interface IJwtProvider {
    String generateToken(User user);
    String generateRefreshToken(User user);
    String extractUsername(String token);
    boolean isValidToken(String token);
}