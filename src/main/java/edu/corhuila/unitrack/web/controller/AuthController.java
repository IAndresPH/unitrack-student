package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.dto.request.AuthRequest;
import edu.corhuila.unitrack.application.dto.request.RegisterRequest;
import edu.corhuila.unitrack.application.dto.response.AuthResponse;
import edu.corhuila.unitrack.application.port.in.IUserService;
import edu.corhuila.unitrack.infrastructure.cookie.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IUserService userService;
    private final CookieUtil cookieUtil;

    public AuthController(IUserService userService, CookieUtil cookieUtil) {
        this.userService = userService;
        this.cookieUtil = cookieUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Void> authenticate(@RequestBody AuthRequest request, HttpServletResponse response) {
        AuthResponse authResponse = userService.authenticate(request);

        // Cookie para el access token (cliente puede usarla)
        cookieUtil.attachTokenCookie(response, authResponse.accessToken());

        // Cookie para el refresh token (solo para backend, HTTP-only y Secure)
        cookieUtil.attachRefreshTokenCookie(response, authResponse.refreshToken());

        return ResponseEntity.ok().build();
    }


    @PostMapping("/refresh")
    public ResponseEntity<Void> refreshTokenFromCookie(
            @CookieValue(name = "refresh-token", required = false) String oldRefreshToken,
            HttpServletResponse response) {

        if (oldRefreshToken == null) {
            return ResponseEntity.status(401).build();
        }

        String newAccessToken = userService.refreshTokenFromCookie(oldRefreshToken);

        // Reemplaza solo el access token
        cookieUtil.attachTokenCookie(response, newAccessToken);

        return ResponseEntity.ok().build();
    }
}