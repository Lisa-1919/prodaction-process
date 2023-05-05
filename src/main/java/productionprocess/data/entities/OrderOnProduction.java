package productionprocess.data.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders_on_production")
public class OrderOnProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "receipt_date")
    private LocalDateTime receiptDate;

    @Column(name = "status")
    private String status;

    @Column(name = "name")
    private String name;

    @Column(name = "desired_date")
    private LocalDateTime desiredDate;

    @Column(name = "total_hours")
    private int totalHours;
    @Column(name="total_minutes")
    private int totalMinutes;

    @OneToMany(mappedBy = "orderOnProduction")
    private List<OrderOnProductionDetails> orderOnProductionDetails;

    public OrderOnProduction() {
        orderOnProductionDetails = new ArrayList<>();
    }

    public OrderOnProduction(LocalDateTime orderDate, LocalDateTime receiptDate, String status, String name, LocalDateTime desiredDate, int totalHours, int totalMinutes, List<OrderOnProductionDetails> orderOnProductionDetails) {
        this.orderDate = orderDate;
        this.receiptDate = receiptDate;
        this.status = status;
        this.name = name;
        this.desiredDate = desiredDate;
        this.totalHours = totalHours;
        this.totalMinutes = totalMinutes;
        this.orderOnProductionDetails = orderOnProductionDetails;
    }

    public OrderOnProduction(Integer id, LocalDateTime orderDate, LocalDateTime receiptDate, String status, String name, LocalDateTime desiredDate, List<OrderOnProductionDetails> orderOnProductionDetails) {
        this.id = id;
        this.orderDate = orderDate;
        this.receiptDate = receiptDate;
        this.status = status;
        this.name = name;
        this.desiredDate = desiredDate;
        this.orderOnProductionDetails = orderOnProductionDetails;
    }

    public OrderOnProduction(LocalDateTime orderDate, LocalDateTime receiptDate, String status, String name, LocalDateTime desiredDate, List<OrderOnProductionDetails> orderOnProductionDetails) {
        this.orderDate = orderDate;
        this.receiptDate = receiptDate;
        this.status = status;
        this.name = name;
        this.desiredDate = desiredDate;
        this.orderOnProductionDetails = orderOnProductionDetails;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(LocalDateTime desiredDate) {
        this.desiredDate = desiredDate;
    }

    public List<OrderOnProductionDetails> getOrderOnProductionDetails() {
        return orderOnProductionDetails;
    }

    public void setOrderOnProductionDetails(List<OrderOnProductionDetails> orderOnProductionDetails) {
        this.orderOnProductionDetails = orderOnProductionDetails;
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
