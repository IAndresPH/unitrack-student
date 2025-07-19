package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.AuthRequest;
import edu.corhuila.unitrack.application.dto.request.RegisterRequest;
import edu.corhuila.unitrack.application.dto.response.AuthResponse;
import edu.corhuila.unitrack.application.mapper.UserMapper;
import edu.corhuila.unitrack.application.port.in.IUserService;
import edu.corhuila.unitrack.application.port.out.IJwtProvider;
import edu.corhuila.unitrack.application.port.out.ITokenPersistencePort;
import edu.corhuila.unitrack.application.port.out.IUserPersistencePort;
import edu.corhuila.unitrack.domain.model.Token;
import edu.corhuila.unitrack.domain.model.TokenType;
import edu.corhuila.unitrack.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IUserService {

    private final UserMapper userMapper;
    private final IUserPersistencePort userPersistencePort;
    private final IJwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final ITokenPersistencePort tokenPersistencePort;

    public AuthService(UserMapper userMapper, IUserPersistencePort userPersistencePort, IJwtProvider jwtProvider, PasswordEncoder passwordEncoder, ITokenPersistencePort tokenPersistencePort) {
        this.userMapper = userMapper;
        this.userPersistencePort = userPersistencePort;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.tokenPersistencePort = tokenPersistencePort;
    }

    @Override
    public void register(RegisterRequest request) {
        boolean exists = userPersistencePort.findByUsernameOrEmail(request.username()).isPresent();
        if (exists) {
            throw new RuntimeException("El usuario ya existe con ese nombre o email.");
        }

        // Crear objeto User
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        userPersistencePort.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userPersistencePort.findByUsernameOrEmail(request.usernameOrEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta.");
        }

        // Generar token
        String token = jwtProvider.generateToken(user);

        // Revocar tokens válidos anteriores
        tokenPersistencePort.revokeAllValidTokensByUserId(user.getId());

        // Guardar nuevo token
        Token newToken = new Token();
        newToken.setToken(token);
        newToken.setTokenType(TokenType.BEARER);
        newToken.setRevoked(false);
        newToken.setExpired(false);
        newToken.setUser(user);
        tokenPersistencePort.save(newToken);

        return new AuthResponse(token);
    }


    @Override
    public AuthResponse refreshToken(String oldToken) {
        Token storedToken = tokenPersistencePort.findByToken(oldToken)
                .orElseThrow(() -> new RuntimeException("Token no registrado"));

        if (storedToken.isRevoked() || storedToken.isExpired()) {
            throw new RuntimeException("Token inválido o ya revocado");
        }

        String username = jwtProvider.extractUsername(oldToken);
        User user = userPersistencePort.findByUsernameOrEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Revocar todos los tokens válidos anteriores
        tokenPersistencePort.revokeAllValidTokensByUserId(user.getId());

        // Crear nuevo token
        String newToken = jwtProvider.generateToken(user);
        Token token = new Token();
        token.setToken(newToken);
        token.setTokenType(TokenType.BEARER);
        token.setRevoked(false);
        token.setExpired(false);
        token.setUser(user);
        tokenPersistencePort.save(token);

        return new AuthResponse(newToken);
    }

}