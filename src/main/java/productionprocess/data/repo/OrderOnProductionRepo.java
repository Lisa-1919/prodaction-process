package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.OrderOnProduction;

public interface OrderOnProductionRepo extends JpaRepository<OrderOnProduction, Integer> {
}
