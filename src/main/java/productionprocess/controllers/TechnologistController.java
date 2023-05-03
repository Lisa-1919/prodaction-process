package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import productionprocess.data.entities.Employee;
import productionprocess.data.entities.Material;
import productionprocess.data.entities.Operation;
import productionprocess.data.entities.Product;
import productionprocess.services.MaterialService;
import productionprocess.services.OperationService;
import productionprocess.services.ProductService;

@Controller
public class TechnologistController {

    @Autowired
    private ProductService productService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private OperationService operationService;

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products", productService.findAll());
        return "technologist";
    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable("id") Integer id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "edit_product";
    }

    @PostMapping("/products/{id}")
    public String editProduct(@ModelAttribute("product") Product product, Model model){
        productService.editProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable("id") Integer id, Model model){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/materials")
    public String materials(Model model){
        model.addAttribute("materials", materialService.findAll());
        return "materials";
    }

    @GetMapping("/materials/{id}")
    public String getMaterial(@PathVariable("id") Integer id, Model model){
        model.addAttribute("material", materialService.findById(id));
        return "edit_material";
    }

    @PostMapping("/materials/{id}")
    public String editMaterial(@ModelAttribute("material") Material material, Model model){
        materialService.editMaterial(material);
        return "redirect:/materials";
    }

    @GetMapping("/materials/{id}/delete")
    public String deleteMaterial(@PathVariable("id") Integer id, Model model){
        materialService.deleteMaterial(id);
        return "redirect:/materials";
    }

    @GetMapping("/operations")
    public String operations(Model model){
        model.addAttribute("operations", materialService.findAll());
        return "operations";
    }

    @GetMapping("/operations/{id}")
    public String getOperation(@PathVariable("id") Integer id, Model model){
        model.addAttribute("operation", operationService.findById(id));
        return "edit_operation";
    }

    @PostMapping("/operations/{id}")
    public String editOperation(@ModelAttribute("operation")Operation operation, Model model){
        operationService.editOperation(operation);
        return "redirect:/operations";
    }

    @GetMapping("/operations/{id}/delete")
    public String deleteOperation(@PathVariable("id") Integer id, Model model){
        operationService.deleteOperation(id);
        return "redirect:/operations";
    }


}
