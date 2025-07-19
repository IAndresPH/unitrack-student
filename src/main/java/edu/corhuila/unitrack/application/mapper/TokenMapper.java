package edu.corhuila.unitrack.application.mapper;

import edu.corhuila.unitrack.application.dto.response.TokenResponse;
import edu.corhuila.unitrack.domain.model.Token;
import edu.corhuila.unitrack.domain.model.TokenType;
import edu.corhuila.unitrack.infrastructure.persistence.entity.TokenEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.TokenTypeEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {

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

    public TokenResponse toResponseDto(Token domain) {
        if (domain == null) return null;

        return new TokenResponse(
                domain.getId(),
                domain.getToken(),
                domain.getTokenType().name(),
                domain.isRevoked(),
                domain.isExpired()
        );
    }
}