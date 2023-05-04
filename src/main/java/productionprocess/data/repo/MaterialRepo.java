package productionprocess.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import productionprocess.data.entities.Material;

public interface MaterialRepo extends JpaRepository<Material, Integer> {
    Material findByName(String name);
}
