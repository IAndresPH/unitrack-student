package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.AuthRequest;
import edu.corhuila.unitrack.application.dto.request.RegisterRequest;

public interface IUserService {
    void register(RegisterRequest request);
    String login(AuthRequest request);
    String refreshTokenFromCookie(String oldToken);
}