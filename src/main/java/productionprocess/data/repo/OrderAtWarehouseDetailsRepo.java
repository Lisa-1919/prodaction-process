package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.OrderAtWarehouseDetails;

public interface OrderAtWarehouseDetailsRepo extends JpaRepository<OrderAtWarehouseDetails, Integer> {
}
