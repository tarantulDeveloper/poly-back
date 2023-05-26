package kg.lovz.server.repo;

import kg.lovz.server.entity.WhoAreLovzKg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhoAreLovzKgRepository extends JpaRepository<WhoAreLovzKg, Integer> {
}
