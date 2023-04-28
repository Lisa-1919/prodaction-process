package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.OperationInRoute;

public interface OperationInRouteRepo extends JpaRepository<OperationInRoute, Integer> {
}
