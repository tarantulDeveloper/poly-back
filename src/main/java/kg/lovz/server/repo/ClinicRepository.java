package kg.lovz.server.repo;

import kg.lovz.server.entity.Clinic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    Page<Clinic> findAllByOblast(String oblast, Pageable pageable);
}
