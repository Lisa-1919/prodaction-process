package productionprocess.data.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders_at_warehouse")
public class OrderAtWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "receipt_date")
    private LocalDateTime receiptDate;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "orderAtWarehouse")
    private List<OrderAtWarehouseDetails> orderAtWarehouseDetails;

    public OrderAtWarehouse() {
        orderAtWarehouseDetails = new ArrayList<>();
    }

    public OrderAtWarehouse(Integer id, LocalDateTime orderDate, LocalDateTime receiptDate, String status, List<OrderAtWarehouseDetails> orderAtWarehouseDetails) {
        this.id = id;
        this.orderDate = orderDate;
        this.receiptDate = receiptDate;
        this.status = status;
        this.orderAtWarehouseDetails = orderAtWarehouseDetails;
    }

    public OrderAtWarehouse(LocalDateTime orderDate, LocalDateTime receiptDate, String status, List<OrderAtWarehouseDetails> orderAtWarehouseDetails) {
        this.orderDate = orderDate;
        this.receiptDate = receiptDate;
        this.status = status;
        this.orderAtWarehouseDetails = orderAtWarehouseDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDateTime receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderAtWarehouseDetails> getOrderAtWarehouseDetails() {
        return orderAtWarehouseDetails;
    }

    public void setOrderAtWarehouseDetails(List<OrderAtWarehouseDetails> orderAtWarehouseDetails) {
        this.orderAtWarehouseDetails = orderAtWarehouseDetails;
    }
}
