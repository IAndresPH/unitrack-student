package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.User;
import java.util.Optional;

public interface IUserPersistencePort {
    User save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String usernameOrEmail);
    Optional<User> findById(Long id);
}