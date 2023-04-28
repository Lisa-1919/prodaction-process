package productionprocess.data.repo;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.OrderAtWarehouse;

public interface OrderAtWarehouseRepo extends JpaRepository<OrderAtWarehouse, Integer> {
}
