package productionprocess.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "operation_in_route")
public class OperationInRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    private Operation operation;

    @Column(name = "sequencing")
    private int sequencing;

    public OperationInRoute() {
    }

    public OperationInRoute(Integer id, Route route, Operation operation, int sequencing) {
        this.id = id;
        this.route = route;
        this.operation = operation;
        this.sequencing = sequencing;
    }

    public OperationInRoute(Route route, Operation operation, int sequencing) {
        this.route = route;
        this.operation = operation;
        this.sequencing = sequencing;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getSequencing() {
        return sequencing;
    }

    public void setSequencing(int sequencing) {
        this.sequencing = sequencing;
    }
}
