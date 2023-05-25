package kg.lovz.server.repo;

import kg.lovz.server.entity.HomeContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeContentRepository extends JpaRepository<HomeContent, Integer> {
    List<HomeContent> findAllByOrderByIdDesc();
}
