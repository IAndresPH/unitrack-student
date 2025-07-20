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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IUserService {

    private final UserMapper userMapper;
    private final IUserPersistencePort userPersistencePort;
    private final IJwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final ITokenPersistencePort tokenPersistencePort;

    public AuthService(UserMapper userMapper, IUserPersistencePort userPersistencePort, IJwtProvider jwtProvider, PasswordEncoder passwordEncoder, ITokenPersistencePort tokenPersistencePort, AuthenticationManager authenticationManager) {
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
    public AuthResponse authenticate(AuthRequest request) {
        User user = userPersistencePort.findByUsernameOrEmail(request.usernameOrEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Revocar tokens antiguos
        tokenPersistencePort.revokeAllValidTokensByUserId(user.getId());

        // Generar nuevos tokens
        String accessToken = jwtProvider.generateToken(user);
        String refreshToken = jwtProvider.generateRefreshToken(user);

        // Guardar tokens
        saveToken(user, accessToken, TokenType.BEARER);
        saveToken(user, refreshToken, TokenType.REFRESH);

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public String refreshTokenFromCookie(String oldToken) {
        // 1. Verifica que el token exista
        Token storedToken = tokenPersistencePort.findByToken(oldToken)
                .orElseThrow(() -> new RuntimeException("Token no registrado"));

        // 2. Verifica que el token sea de tipo REFRESH
        if (storedToken.getTokenType() != TokenType.REFRESH) {
            throw new RuntimeException("Token no es de tipo REFRESH");
        }

        // 3. Verifica que el token NO esté revocado
        if (storedToken.isRevoked()) {
            throw new RuntimeException("Token revocado");
        }

        // 4. Extrae al usuario del token
        String username = jwtProvider.extractUsername(oldToken);
        User user = userPersistencePort.findByUsernameOrEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 5. Revoca los access tokens válidos existentes (NO revocar el refresh)
        tokenPersistencePort.revokeAllValidTokensByUserId(user.getId());

        // 6. Genera un nuevo access token
        String newAccessToken = jwtProvider.generateToken(user);

        // 7. Guarda el nuevo access token en la base de datos
        saveToken(user, newAccessToken, TokenType.BEARER);

        // 8. Devuelve el nuevo access token
        return newAccessToken;
    }

    private void saveToken(User user, String tokenStr, TokenType tokenType) {
        Token token = new Token();
        token.setToken(tokenStr);
        token.setTokenType(tokenType);
        token.setRevoked(false);
        token.setExpired(false);
        token.setUser(user);
        tokenPersistencePort.save(token);
    }
}