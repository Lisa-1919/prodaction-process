package productionprocess.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_at_warehouse_details")
public class OrderAtWarehouseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderAtWarehouse orderAtWarehouse;

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Material material;

    @Column(name = "amount")
    private double amount;

    public OrderAtWarehouseDetails() {
    }

    public OrderAtWarehouseDetails(Integer id, OrderAtWarehouse orderAtWarehouse, Material material, double amount) {
        this.id = id;
        this.orderAtWarehouse = orderAtWarehouse;
        this.material = material;
        this.amount = amount;
    }

    public OrderAtWarehouseDetails(OrderAtWarehouse orderAtWarehouse, Material material, double amount) {
        this.orderAtWarehouse = orderAtWarehouse;
        this.material = material;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderAtWarehouse getOrderAtWarehouse() {
        return orderAtWarehouse;
    }

    public void setOrderAtWarehouse(OrderAtWarehouse orderAtWarehouse) {
        this.orderAtWarehouse = orderAtWarehouse;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
