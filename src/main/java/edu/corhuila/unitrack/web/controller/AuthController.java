package edu.corhuila.unitrack.web.controller;

import edu.corhuila.unitrack.application.dto.request.AuthRequest;
import edu.corhuila.unitrack.application.dto.request.RegisterRequest;
import edu.corhuila.unitrack.application.dto.response.AuthResponse;
import edu.corhuila.unitrack.application.dto.response.UserResponse;
import edu.corhuila.unitrack.application.port.in.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = userService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        UserResponse user = userService.getCurrentUser();
        return ResponseEntity.ok(user);
    }
}