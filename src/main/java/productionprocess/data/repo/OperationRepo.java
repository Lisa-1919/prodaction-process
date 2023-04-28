package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.Operation;

public interface OperationRepo extends JpaRepository<Operation, Integer> {
}
