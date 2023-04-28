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

    @Column(name = "total_time")
    private LocalTime totalTime;

    @OneToMany(mappedBy = "product")
    private List<OperationInRoute> operationInRoutes;

    public Route() {
        operationInRoutes = new ArrayList<>();
    }

    public Route(Integer id, String name, LocalTime totalTime, List<OperationInRoute> operationInRoutes) {
        this.id = id;
        this.name = name;
        this.totalTime = totalTime;
        this.operationInRoutes = operationInRoutes;
    }

    public Route(String name, LocalTime totalTime, List<OperationInRoute> operationInRoutes) {
        this.name = name;
        this.totalTime = totalTime;
        this.operationInRoutes = operationInRoutes;
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

    public LocalTime getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(LocalTime totalTime) {
        this.totalTime = totalTime;
    }
}
