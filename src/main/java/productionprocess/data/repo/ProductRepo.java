package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
