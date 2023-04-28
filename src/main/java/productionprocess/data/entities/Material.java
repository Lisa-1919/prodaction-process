package productionprocess.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "stock_quantity")
    private double stockQuantity;

    @Column(name = "unit")
    private String unit;

    public Material() {
    }

    public Material(Integer id, String type, String name, double stockQuantity, String unit) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unit = unit;
    }

    public Material(String type, String name, double stockQuantity, String unit) {
        this.type = type;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
