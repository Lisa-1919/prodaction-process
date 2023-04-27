package productionprocess.data.repo;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {
}
