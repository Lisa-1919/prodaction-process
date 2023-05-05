package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.MaterialsForProduct;
import productionprocess.data.entities.Product;

import java.util.List;

public interface MaterialsForProductRepo extends JpaRepository<MaterialsForProduct, Integer> {
    List<MaterialsForProduct> findByProduct(Product product);
}
