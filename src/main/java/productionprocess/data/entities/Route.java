package productionprocess.data.entities;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "total_hours")
    private int totalHours;
    @Column(name="total_minutes")
    private int totalMinutes;

    @OneToMany(mappedBy = "route")
    private List<OperationInRoute> operationInRoutes;

    public Route() {
        operationInRoutes = new ArrayList<>();
    }

    public Route(Integer id, String name, int totalHours, int totalMinutes, List<OperationInRoute> operationInRoutes) {
        this.id = id;
        this.name = name;
        this.totalHours = totalHours;
        this.totalMinutes = totalMinutes;
        this.operationInRoutes = operationInRoutes;
    }

    public Route(String name, int totalHours, int totalMinutes) {
        this.name = name;
        this.totalHours = totalHours;
        this.totalMinutes = totalMinutes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OperationInRoute> getOperationInRoutes() {
        return operationInRoutes;
    }

    public void setOperationInRoutes(List<OperationInRoute> operationInRoutes) {
        this.operationInRoutes = operationInRoutes;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }
}
