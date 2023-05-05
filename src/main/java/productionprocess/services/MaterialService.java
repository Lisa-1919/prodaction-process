package productionprocess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionprocess.data.entities.Material;
import productionprocess.data.repo.MaterialRepo;
import productionprocess.data.repo.MaterialsForProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MaterialService {
    @Autowired
    MaterialRepo materialRepo;

    @Autowired
    MaterialsForProductRepo materialsForProductRepo;

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
}
