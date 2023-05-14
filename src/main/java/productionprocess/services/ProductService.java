package productionprocess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionprocess.data.entities.MaterialsForProduct;
import productionprocess.data.entities.Product;
import productionprocess.data.entities.Route;
import productionprocess.data.repo.MaterialsForProductRepo;
import productionprocess.data.repo.OperationInRouteRepo;
import productionprocess.data.repo.ProductRepo;
import productionprocess.data.repo.RouteRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private RouteRepo routeRepo;
    @Autowired
    private MaterialsForProductRepo materialsForProductRepo;
    @Autowired
    private OperationInRouteRepo operationInRouteRepo;

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(int id) {
        return productRepo.findById(id).orElseThrow();
    }

    public void editProduct(Product product) {
        Product productDB = productRepo.findById(product.getId()).orElseThrow();

        Route route = product.getRoute();
        Route routeDB = routeRepo.findById(productDB.getRoute().getId()).orElseThrow();

        routeDB.setName(route.getName());
        routeDB.setTotalHours(route.getTotalHours());
        routeDB.setTotalMinutes(route.getTotalMinutes());
        routeDB.setOperationInRoutes(route.getOperationInRoutes());

        routeRepo.save(routeDB);

        List<MaterialsForProduct> materialsForProductList = materialsForProductRepo.findByProduct(productDB);
        materialsForProductRepo.deleteAll(materialsForProductList);
        product.getMaterialsForProducts().forEach(materialsForProduct -> materialsForProduct.setProduct(product));
        materialsForProductRepo.saveAll(product.getMaterialsForProducts());

        productDB.setType(product.getType());
        productDB.setModel(product.getModel());
        productDB.setProperty(product.getProperty());
        productDB.setCostPrice(product.getCostPrice());
        productDB.setRoute(product.getRoute());
        productDB.setMaterialsForProducts(product.getMaterialsForProducts());
        productRepo.save(productDB);
    }

    public boolean deleteProduct(int id) {
        try {
            Product product = productRepo.findById(id).orElseThrow();
            routeRepo.delete(product.getRoute());
            productRepo.delete(product);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean existsById(int id) {
        return productRepo.existsById(id);
    }

    public boolean addProduct(Product product) {
        List<Product> products = productRepo.findByArticle(product.getArticle());
        if (products.isEmpty() || products == null) {
            routeRepo.save(product.getRoute());
            operationInRouteRepo.saveAll(product.getRoute().getOperationInRoutes());
            productRepo.save(product);
            product.getMaterialsForProducts().forEach(materialsForProduct -> materialsForProduct.setProduct(product));
            materialsForProductRepo.saveAll(product.getMaterialsForProducts());
            return true;
        } else return false;
    }

    public List<Product> searchProductByArticle(String value) {
        Iterable<Product> products = productRepo.findAll();
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getArticle().toLowerCase().contains(value.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

}
