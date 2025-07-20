package edu.corhuila.unitrack.infrastructure.jwt;

import edu.corhuila.unitrack.application.port.out.IJwtProvider;
import edu.corhuila.unitrack.application.port.out.IUserPersistencePort;
import edu.corhuila.unitrack.domain.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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

        // Buscar token únicamente en cookies
        String token = null;

        if (request.getCookies() != null) {
            for (var cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {
            try {
                String username = jwtProvider.extractUsername(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User user = userPersistencePort.findByUsername(username).orElse(null);

                    if (user != null && jwtProvider.isValidToken(token)) {
                        var auth = new UsernamePasswordAuthenticationToken(user, null, List.of());
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            } catch (ExpiredJwtException e) {
                request.setAttribute("jwt_error", "expired");
            } catch (JwtException e) {
                request.setAttribute("jwt_error", "invalid");
            }
        }

        filterChain.doFilter(request, response);
    }
}