package productionprocess.data.model;

public class MaterialToOrder {
    private String name;
    private double stockQuantity;
    private double necessaryQuantity;
    private double orderedQuantity;

    public MaterialToOrder(){}

    public MaterialToOrder(String name, double stockQuantity, double necessaryQuantity, double orderedQuantity) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.necessaryQuantity = necessaryQuantity;
        this.orderedQuantity = orderedQuantity;
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

    public double getNecessaryQuantity() {
        return necessaryQuantity;
    }

    public void setNecessaryQuantity(double necessaryQuantity) {
        this.necessaryQuantity = necessaryQuantity;
    }

    public double getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(double orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }
}
