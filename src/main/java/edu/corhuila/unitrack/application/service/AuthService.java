package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.AuthRequest;
import edu.corhuila.unitrack.application.dto.request.RegisterRequest;
import edu.corhuila.unitrack.application.dto.response.AuthResponse;
import edu.corhuila.unitrack.application.dto.response.UserResponse;
import edu.corhuila.unitrack.application.mapper.UserMapper;
import edu.corhuila.unitrack.application.port.in.IUserService;
import edu.corhuila.unitrack.application.port.out.IJwtProvider;
import edu.corhuila.unitrack.application.port.out.IUserPersistencePort;
import edu.corhuila.unitrack.domain.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IUserService {

    private final UserMapper userMapper;
    private final IUserPersistencePort userPersistencePort;
    private final IJwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserMapper userMapper, IUserPersistencePort userPersistencePort, IJwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userPersistencePort = userPersistencePort;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        // Verifica si ya existe usuario con mismo username o email
        boolean exists = userPersistencePort.findByUsernameOrEmail(request.username()).isPresent();
        if (exists) {
            throw new RuntimeException("El usuario ya existe con ese nombre o email.");
        }

        // Crear objeto User
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        // Guardar en BD
        User savedUser = userPersistencePort.save(user);

        // Generar token
        String token = jwtProvider.generateToken(savedUser);

        // Mapear respuesta
        return new AuthResponse(token, userMapper.toResponse(savedUser));
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        // Buscar usuario por username o email
        User user = userPersistencePort.findByUsernameOrEmail(request.usernameOrEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Verificar contraseña (aquí deberías usar BCrypt o similar)
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta.");
        }

        String token = jwtProvider.generateToken(user);

        return new AuthResponse(token, userMapper.toResponse(user));
    }

    @Override
    public UserResponse getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No hay usuario autenticado.");
        }

        String username = authentication.getName();
        User user = userPersistencePort.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        return userMapper.toResponse(user);
    }
}