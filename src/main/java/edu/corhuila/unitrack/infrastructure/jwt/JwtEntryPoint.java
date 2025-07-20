package edu.corhuila.unitrack.infrastructure.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String errorType = (String) request.getAttribute("jwt_error");
        String message;

        if ("expired".equals(errorType)) {
            message = "Token expirado";
        } else if ("invalid".equals(errorType)) {
            message = "Token inválido";
        } else {
            message = "No autorizado o token no encontrado";
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"error\": \"No autorizado o token inválido\"}");
    }
}