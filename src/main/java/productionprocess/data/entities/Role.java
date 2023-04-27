package productionprocess.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="role_name")
    private String roleName;

    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role() {
    }
}
