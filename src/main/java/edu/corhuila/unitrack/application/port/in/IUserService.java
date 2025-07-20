package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.AuthRequest;
import edu.corhuila.unitrack.application.dto.request.RegisterRequest;
import edu.corhuila.unitrack.application.dto.response.AuthResponse;

public interface IUserService {
    void register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);
    String refreshTokenFromCookie(String oldToken);
}