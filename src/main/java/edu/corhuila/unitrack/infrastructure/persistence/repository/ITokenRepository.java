package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByToken(String token);

    @Query("SELECT t FROM tokens t WHERE t.userEntity.id = :userId AND (t.isExpired = false AND t.isRevoked = false)")
    List<TokenEntity> findAllValidTokensByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE tokens t SET t.isRevoked = true, t.isExpired = true WHERE t.userEntity.id = :userId AND (t.isExpired = false AND t.isRevoked = false)")
    void revokeAllValidTokensByUserId(Long userId);
}