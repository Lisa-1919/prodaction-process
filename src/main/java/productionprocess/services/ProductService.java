package productionprocess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionprocess.data.entities.Product;
import productionprocess.data.repo.ProductRepo;
import productionprocess.data.repo.RouteRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    RouteRepo routeRepo;

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(int id) {
        return productRepo.findById(id).orElseThrow();
    }

    public void editProduct(Product product) {
        Product productDB = productRepo.findById(product.getId()).orElseThrow();
        productDB.setType(product.getType());
        productDB.setModel(product.getModel());
        productDB.setRodSize(product.getRodSize());
        productDB.setPaintKind(product.getPaintKind());
        productDB.setCostPrice(product.getCostPrice());
        productDB.setRoute(product.getRoute());
        productDB.setMaterialsForProducts(product.getMaterialsForProducts());
        productRepo.save(productDB);
    }

    public boolean deleteProduct(int id) {
        try{
            Product product = productRepo.findById(id).orElseThrow();
            productRepo.delete(product);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }

    }

    public boolean existsById(int id) {
        return productRepo.existsById(id);
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public List<Product> searchProductByModel(String value) {
        Iterable<Product> products = productRepo.findAll();
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getModel().toLowerCase().contains(value.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

}