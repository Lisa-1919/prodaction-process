package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.Operation;
import productionprocess.data.entities.OperationInRoute;

import java.util.List;

public interface OperationInRouteRepo extends JpaRepository<OperationInRoute, Integer> {
    List<OperationInRoute> findByOperation(Operation operation);
}
