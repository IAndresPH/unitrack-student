package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.domain.model.Token;
import edu.corhuila.unitrack.domain.model.TokenType;
import edu.corhuila.unitrack.domain.model.User;
import edu.corhuila.unitrack.infrastructure.persistence.entity.TokenEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.TokenTypeEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class TokenEntityMapper {

    public Token toDomain(TokenEntity entity) {
        if (entity == null) return null;

        Token token = new Token();
        token.setId(entity.getId());
        token.setToken(entity.getToken());
        token.setTokenType(TokenType.valueOf(entity.getTokenTypeEntity().name()));
        token.setRevoked(entity.isRevoked());
        token.setExpired(entity.isExpired());

        // Solo establecer el ID del usuario, para evitar acoplar entidades
        User user = new User();
        user.setId(entity.getUserEntity().getId());
        token.setUser(user);

        return token;
    }

    public TokenEntity toEntity(Token domain, UserEntity userEntity) {
        if (domain == null) return null;

        TokenEntity entity = TokenEntity.builder()
                .token(domain.getToken())
                .tokenTypeEntity(TokenTypeEntity.valueOf(domain.getTokenType().name()))
                .isRevoked(domain.isRevoked())
                .isExpired(domain.isExpired())
                .userEntity(userEntity)
                .build();

        entity.setId(domain.getId());
        return entity;
    }
}