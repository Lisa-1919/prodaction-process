package productionprocess.data.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "model")
    private String model;

    @Column(name = "cost_price")
    private double costPrice;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

    @OneToMany(mappedBy = "product")
    private List<MaterialsForProduct> materialsForProducts;

    @Column(name="article")
    private String article;

    @Column(name="property")
    private String property;

    public Product() {
        this.materialsForProducts = new ArrayList<>();
    }

    public Product(Integer id, String type, String model, double costPrice, Route route, List<MaterialsForProduct> materialsForProducts, String article, String property) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.costPrice = costPrice;
        this.route = route;
        this.materialsForProducts = materialsForProducts;
        this.article = article;
        this.property = property;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<MaterialsForProduct> getMaterialsForProducts() {
        return materialsForProducts;
    }

    public void setMaterialsForProducts(List<MaterialsForProduct> materialsForProducts) {
        this.materialsForProducts = materialsForProducts;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
