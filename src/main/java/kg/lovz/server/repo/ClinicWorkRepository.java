package kg.lovz.server.repo;

import kg.lovz.server.entity.ClinicWork;
import kg.lovz.server.repo.projections.ClinicWorkListView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicWorkRepository extends JpaRepository<ClinicWork, Integer> {
    boolean existsByName(String name);
    List<ClinicWorkListView> findAllProjectedBy();
    Optional<ClinicWork> findByName(String name);
}
