package kg.lovz.server.repo;

import kg.lovz.server.entity.RefreshToken;
import kg.lovz.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByUser(User user);

    boolean existsByUser(User user);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
