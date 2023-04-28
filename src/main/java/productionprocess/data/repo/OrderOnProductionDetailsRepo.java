package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.OrderOnProductionDetails;

public interface OrderOnProductionDetailsRepo extends JpaRepository<OrderOnProductionDetails, Integer> {
}
