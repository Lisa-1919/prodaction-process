package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    Employee findByEmail(String email);
}
