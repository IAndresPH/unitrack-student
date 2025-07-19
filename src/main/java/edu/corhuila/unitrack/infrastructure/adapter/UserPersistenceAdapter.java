package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.mapper.UserMapper;
import edu.corhuila.unitrack.application.port.out.IUserPersistencePort;
import edu.corhuila.unitrack.domain.model.User;
import edu.corhuila.unitrack.infrastructure.persistence.repository.IUserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserPersistenceAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    public UserPersistenceAdapter(IUserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        var entity = userMapper.toEntity(user);
        var saved = userRepository.save(entity);
        return userMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsernameWithStudentId(String username) {
        return userRepository.findByUsernameWithStudentEntity(username)
                .map(userMapper::toDomain);
    }
}