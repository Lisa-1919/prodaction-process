package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.Route;

public interface RouteRepo extends JpaRepository<Route, Integer> {
}
