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
        int routeToDelete = productDB.getRoute().getId();
        productDB.setType(product.getType());
        productDB.setModel(product.getModel());
        productDB.setProperty(product.getProperty());
        productDB.setCostPrice(product.getCostPrice());
        productDB.setRoute(product.getRoute());
        routeRepo.save(product.getRoute());
        operationInRouteRepo.saveAll(product.getRoute().getOperationInRoutes());
        productRepo.save(productDB);
        materialsForProductRepo.deleteAll(productDB.getMaterialsForProducts());
        product.getMaterialsForProducts().forEach(material -> material.setProduct(productDB));
        materialsForProductRepo.saveAll(product.getMaterialsForProducts());
        routeRepo.delete(routeRepo.findById(routeToDelete).get());
    }

    public boolean deleteProduct(int id) {
        try {
            Product product = productRepo.findById(id).orElseThrow();
            Route route = product.getRoute();
            productRepo.delete(product);
            routeRepo.delete(route);

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

    public Route getRoute(Product product){
        Route route = new Route();
        for(Route route1: routeRepo.findAll()){
            if(productRepo.findById(product.getId()).get().getRoute().getId() == route1.getId()){
                route = route1;
            }
        }
        return route;
    }
}
