package kg.lovz.server.repo;

import kg.lovz.server.entity.CarouselItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarouselItemRepository extends JpaRepository<CarouselItem, Integer> {
}
