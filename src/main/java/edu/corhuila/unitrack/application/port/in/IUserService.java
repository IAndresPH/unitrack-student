package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.AuthRequest;
import edu.corhuila.unitrack.application.dto.request.RegisterRequest;
import edu.corhuila.unitrack.application.dto.response.AuthResponse;
import edu.corhuila.unitrack.application.dto.response.UserResponse;

public interface IUserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    UserResponse getCurrentUser();
}