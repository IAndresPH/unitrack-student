package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.dto.request.AuthRequest;
import edu.corhuila.unitrack.application.dto.request.RegisterRequest;
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

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody AuthRequest request, HttpServletResponse response) {
        String token = userService.login(request);
        cookieUtil.attachTokenCookie(response, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> refreshTokenFromCookie(
            @CookieValue(name = CookieUtil.COOKIE_NAME, required = false) String oldToken,
            HttpServletResponse response) {

        if (oldToken == null) {
            return ResponseEntity.status(401).build();
        }

        String newToken = userService.refreshTokenFromCookie(oldToken);
        cookieUtil.attachTokenCookie(response, newToken);
        return ResponseEntity.ok().build();
    }
}