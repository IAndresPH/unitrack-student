package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.Token;

import java.util.Optional;

public interface ITokenPersistencePort {
    void save(Token token);
    Optional<Token> findByToken(String token);
    void revokeAllValidTokensByUserId(Long userId);
}