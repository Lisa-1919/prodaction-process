package productionprocess.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_on_production_details")
public class OrderOnProductionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderOnProduction orderOnProduction;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "amount")
    private double amount;

    public OrderOnProductionDetails() {
    }

    public OrderOnProductionDetails(Integer id, OrderOnProduction orderOnProduction, Product product, double amount) {
        this.id = id;
        this.orderOnProduction = orderOnProduction;
        this.product = product;
        this.amount = amount;
    }

    public OrderOnProductionDetails(OrderOnProduction orderOnProduction, Product product, double amount) {
        this.orderOnProduction = orderOnProduction;
        this.product = product;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderOnProduction getOrderOnProduction() {
        return orderOnProduction;
    }

    public void setOrderOnProduction(OrderOnProduction orderOnProduction) {
        this.orderOnProduction = orderOnProduction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
