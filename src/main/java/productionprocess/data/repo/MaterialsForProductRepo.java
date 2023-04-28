package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.MaterialsForProduct;

public interface MaterialsForProductRepo extends JpaRepository<MaterialsForProduct, Integer> {
}
