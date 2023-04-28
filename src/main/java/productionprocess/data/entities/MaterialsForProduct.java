package productionprocess.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "materials_for_product")
public class MaterialsForProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Material material;

    @Column(name = "quantity")
    private double quantity;

    public MaterialsForProduct() {
    }

    public MaterialsForProduct(Integer id, Product product, Material material, double quantity) {
        this.id = id;
        this.product = product;
        this.material = material;
        this.quantity = quantity;
    }

    public MaterialsForProduct(Product product, Material material, double quantity) {
        this.product = product;
        this.material = material;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
