package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.port.out.ITokenPersistencePort;
import edu.corhuila.unitrack.domain.model.Token;
import edu.corhuila.unitrack.infrastructure.mapper.TokenEntityMapper;
import edu.corhuila.unitrack.infrastructure.persistence.entity.TokenEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.UserEntity;
import edu.corhuila.unitrack.infrastructure.persistence.repository.ITokenRepository;
import edu.corhuila.unitrack.infrastructure.persistence.repository.IUserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TokenPersistenceAdapter implements ITokenPersistencePort {

    private final ITokenRepository tokenRepository;
    private final IUserRepository userRepository;
    private final TokenEntityMapper tokenEntityMapper;

    public TokenPersistenceAdapter(ITokenRepository tokenRepository, IUserRepository userRepository, TokenEntityMapper tokenEntityMapper) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.tokenEntityMapper = tokenEntityMapper;
    }

    @Override
    public void save(Token token) {
        var userEntity = new UserEntity(); // no hace consulta
        userEntity.setId(token.getUser().getId()); // solo asignas el ID

        TokenEntity entity = tokenEntityMapper.toEntity(token, userEntity);
        tokenRepository.save(entity);
    }

    @Override
    public Optional<Token> findByToken(String tokenStr) {
        return tokenRepository.findByToken(tokenStr)
                .map(tokenEntityMapper::toDomain);
    }

    @Override
    public void revokeAllValidTokensByUserId(Long userId) {
        tokenRepository.revokeAllValidTokensByUserId(userId);
    }
}