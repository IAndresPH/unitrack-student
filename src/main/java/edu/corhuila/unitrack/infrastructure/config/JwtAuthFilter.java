package edu.corhuila.unitrack.infrastructure.config;

import edu.corhuila.unitrack.application.port.out.IJwtProvider;
import edu.corhuila.unitrack.application.port.out.IUserPersistencePort;
import edu.corhuila.unitrack.domain.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final IJwtProvider jwtProvider;
    private final IUserPersistencePort userPersistencePort;

    public JwtAuthFilter(IJwtProvider jwtProvider, IUserPersistencePort userPersistencePort) {
        this.jwtProvider = jwtProvider;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtProvider.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userPersistencePort.findByUsername(username).orElse(null);

                if (user != null && jwtProvider.isValidToken(token, user)) {
                    var auth = new UsernamePasswordAuthenticationToken(
                            username, null, List.of() // Puedes añadir roles aquí
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}