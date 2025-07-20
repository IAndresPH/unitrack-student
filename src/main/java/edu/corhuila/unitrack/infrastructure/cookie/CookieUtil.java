package edu.corhuila.unitrack.infrastructure.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    public static final String COOKIE_NAME = "token";

    public void attachTokenCookie(HttpServletResponse response, String token) {
        var cookie = new Cookie(COOKIE_NAME, token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 días

        response.addCookie(cookie);
    }

    public void attachRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        Cookie cookie = new Cookie("refresh-token", refreshToken);
        cookie.setHttpOnly(true); // El navegador no puede leerla con JS
        cookie.setSecure(false);   // Solo por HTTPS (en producción)
        cookie.setPath("/api/auth/refresh"); // solo se envía a ese endpoint
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 días por ejemplo
        response.addCookie(cookie);
    }
}