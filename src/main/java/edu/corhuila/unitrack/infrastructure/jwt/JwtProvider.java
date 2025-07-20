package edu.corhuila.unitrack.infrastructure.jwt;

import edu.corhuila.unitrack.application.port.out.IJwtProvider;
import edu.corhuila.unitrack.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider implements IJwtProvider {

    @Value("${jwt.secret}")
    private String secret;   // Base64 codificado

    @Value("${jwt.expiration}")
    private long expiration; // en milisegundos

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    private SecretKey key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String generateRefreshToken(User user) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshExpiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }


    private Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String extractUsername(String token) {
        try {
            return getClaims(token).getSubject(); // token válido
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();    // token expirado, aún útil para refresh
        }
    }

    @Override
    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date());
    }
}