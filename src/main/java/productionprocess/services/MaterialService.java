package productionprocess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionprocess.data.entities.*;
import productionprocess.data.model.MaterialToOrder;
import productionprocess.data.repo.MaterialRepo;
import productionprocess.data.repo.MaterialsForProductRepo;
import productionprocess.data.repo.OrderOnProductionRepo;

import java.util.*;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepo materialRepo;

    @Autowired
    private MaterialsForProductRepo materialsForProductRepo;
    @Autowired
    private OrderOnProductionRepo orderOnProductionRepo;

    public List<Material> findAll() {
        return materialRepo.findAll();
    }

    public Material findById(int id) {
        return materialRepo.findById(id).orElseThrow();
    }

    public void editMaterial(Material material) {
        Material materialDB = materialRepo.findById(material.getId()).orElseThrow();
        materialDB.setName(material.getName());
        materialDB.setStockQuantity(material.getStockQuantity());
        materialDB.setUnit(material.getUnit());
        materialRepo.save(materialDB);
    }

    public boolean deleteMaterial(int id) {
        try{
            Material material = materialRepo.findById(id).orElseThrow();
            materialRepo.delete(material);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }

    }

    public boolean existsById(int id) {
        return materialRepo.existsById(id);
    }

    public void addMaterial(Material material) {
        materialRepo.save(material);
    }

    public List<Material> searchMaterialByName(String value) {
        Iterable<Material> materials = materialRepo.findAll();
        List<Material> result = new ArrayList<>();
        for (Material material : materials) {
            if (material.getName().toLowerCase().contains(value.toLowerCase())) {
                result.add(material);
            }
        }
        return result;
    }

    public Material findByName(String name){
        return materialRepo.findByName(name);
    }

    public void startProduction(Integer orderId){
        OrderOnProduction orderOnProduction = orderOnProductionRepo.findById(orderId).get();
        List<OrderOnProductionDetails> orderOnProductionDetails = orderOnProduction.getOrderOnProductionDetails();
        List<Material> materials = materialRepo.findAll();
        List<MaterialToOrder> materialToOrders = new ArrayList<>();
        HashMap<String, Double> materialNameAndNecessaryQuantity = new HashMap<>();
        for (OrderOnProductionDetails orderOnProductionDetail : orderOnProductionDetails) {
            Product product = orderOnProductionDetail.getProduct();
            double amount = orderOnProductionDetail.getAmount();
            for (MaterialsForProduct materialsForProduct : product.getMaterialsForProducts()) {
                if (materialNameAndNecessaryQuantity.containsKey(materialsForProduct.getMaterial().getName())) {
                    materialNameAndNecessaryQuantity.put(materialsForProduct.getMaterial().getName(),
                            materialNameAndNecessaryQuantity.get(materialsForProduct.getMaterial().getName())
                                    + materialsForProduct.getQuantity() * amount);
                } else {
                    materialNameAndNecessaryQuantity.put(materialsForProduct.getMaterial().getName(),
                            materialsForProduct.getQuantity() * amount);
                }
            }
        }

        for (Map.Entry<String, Double> map : materialNameAndNecessaryQuantity.entrySet()) {
            String name = map.getKey();
            Material material = materialRepo.findByName(name);
            double stockQuantity = material.getStockQuantity();
            double necessaryQuantity = map.getValue();
            double orderedQuantity = necessaryQuantity - stockQuantity;
            if (orderedQuantity < 0) {
                orderedQuantity = 0;
            }
            materialToOrders.add(new MaterialToOrder(name, stockQuantity, necessaryQuantity, orderedQuantity));
        }
        for(Material material: materials){
            for(MaterialToOrder materialToOrder: materialToOrders){
                if(material.getName().equals(materialToOrder.getName())){
                    material.setStockQuantity(material.getStockQuantity() - materialToOrder.getNecessaryQuantity());
                }
            }
        }
        materialRepo.saveAll(materials);
    }
}
