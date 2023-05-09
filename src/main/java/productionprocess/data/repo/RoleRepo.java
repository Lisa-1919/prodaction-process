package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findFirstByRoleName(String roleName);
}
