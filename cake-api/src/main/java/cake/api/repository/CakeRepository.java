package cake.api.repository;

import cake.api.model.CakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<CakeEntity, Integer> {

    CakeEntity getBySlug(String slug);
}
