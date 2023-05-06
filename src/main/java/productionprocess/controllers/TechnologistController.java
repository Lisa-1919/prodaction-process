package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import productionprocess.data.entities.*;
import productionprocess.services.MaterialService;
import productionprocess.services.OperationService;
import productionprocess.services.ProductService;

import java.util.List;

@Controller
public class TechnologistController {

    @Autowired
    private ProductService productService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private OperationService operationService;

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "technologist";
    }

    @GetMapping("/products/search")
    public String productSearch(@RequestParam("article") String article, Model model){
        model.addAttribute("products", productService.searchProductByArticle(article));
        return "technologist";
    }

    @GetMapping("/products/add")
    public String addProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("operations", operationService.findAll());
        model.addAttribute("materials", materialService.findAll());
        return "add_product";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("selectedOperations") List<Integer> selectedOperations,
                             @RequestParam("selectedOperationsSequencing") List<Integer> selectedOperationsSequencing,
                             @RequestParam("selectedMaterials") List<Integer> selectedMaterials,
                             @RequestParam("selectedMaterialsQuantity") List<Double> selectedMaterialsQuantity, Model model) {

        Route route = new Route();
        for (int i = 0; i < selectedOperations.size(); i++) {
            OperationInRoute operationInRoute = new OperationInRoute();
            operationInRoute.setOperation(operationService.findById(selectedOperations.get(i)));
            operationInRoute.setSequencing(selectedOperationsSequencing.get(i));
            operationInRoute.setRoute(route);
            route.getOperationInRoutes().add(operationInRoute);
        }
        int hours = 0;
        int minutes = 0;
        for (OperationInRoute operation : route.getOperationInRoutes()) {
            hours += operation.getOperation().getHours();
            minutes += operation.getOperation().getMinutes();
            if (minutes >= 60) {
                hours += minutes / 60;
                minutes = minutes % 60;
            }
        }
        route.setTotalHours(hours);
        route.setTotalMinutes(minutes);
        route.setName(product.getArticle());
        product.setRoute(route);

        for (int i = 0; i < selectedMaterials.size(); i++) {
            MaterialsForProduct materialsForProduct = new MaterialsForProduct();
            materialsForProduct.setMaterial(materialService.findById(selectedMaterials.get(i)));
            materialsForProduct.setQuantity(selectedMaterialsQuantity.get(i));
            product.getMaterialsForProducts().add(materialsForProduct);
        }
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("operations", operationService.findAll());
        model.addAttribute("materials", materialService.findAll());
        return "edit_product";
    }

    @PostMapping("/products/{id}")
    public String editProduct(@ModelAttribute("product") Product product, @RequestParam("selectedOperations") List<Integer> selectedOperations,
                              @RequestParam("selectedOperationsSequencing") List<Integer> selectedOperationsSequencing,
                              @RequestParam("selectedMaterials") List<Integer> selectedMaterials,
                              @RequestParam("selectedMaterialsQuantity") List<Double> selectedMaterialsQuantity, Model model) {

        Route route = new Route();
        for (int i = 0; i < selectedOperations.size(); i++) {
            OperationInRoute operationInRoute = new OperationInRoute();
            operationInRoute.setOperation(operationService.findById(selectedOperations.get(i)));
            operationInRoute.setSequencing(selectedOperationsSequencing.get(i));
            operationInRoute.setRoute(route);
            route.getOperationInRoutes().add(operationInRoute);
        }
        int hours = 0;
        int minutes = 0;
        for (OperationInRoute operation : route.getOperationInRoutes()) {
            hours += operation.getOperation().getHours();
            minutes += operation.getOperation().getMinutes();
            if (minutes >= 60) {
                hours += minutes / 60;
                minutes = minutes % 60;
            }
        }
        route.setTotalHours(hours);
        route.setTotalMinutes(minutes);
        route.setName(product.getArticle());
        product.setRoute(route);

        for (int i = 0; i < selectedMaterials.size(); i++) {
            MaterialsForProduct materialsForProduct = new MaterialsForProduct();
            materialsForProduct.setMaterial(materialService.findById(selectedMaterials.get(i)));
            materialsForProduct.setQuantity(selectedMaterialsQuantity.get(i));
            product.getMaterialsForProducts().add(materialsForProduct);
        }
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/materials")
    public String materials(Model model) {
        model.addAttribute("materials", materialService.findAll());
        return "materials";
    }

    @GetMapping("/materials/search")
    public String materialSearch(@RequestParam("name") String name, Model model){
        model.addAttribute("materials", materialService.searchMaterialByName("name"));
        return "technologist";
    }


    @GetMapping("/materials/add")
    public String addMaterialPage(Model model) {
        model.addAttribute("material", new Material());
        return "add_material";
    }

    @PostMapping("/materials/add")
    public String addMaterial(@ModelAttribute("material") Material material, Model model) {
        materialService.addMaterial(material);
        return "redirect:/materials";
    }

    @GetMapping("/materials/{id}")
    public String getMaterial(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("material", materialService.findById(id));
        return "edit_material";
    }

    @PostMapping("/materials/{id}")
    public String editMaterial(@ModelAttribute("material") Material material, Model model) {
        materialService.editMaterial(material);
        return "redirect:/materials";
    }

    @GetMapping("/materials/{id}/delete")
    public String deleteMaterial(@PathVariable("id") Integer id, Model model) {
        materialService.deleteMaterial(id);
        return "redirect:/materials";
    }

    @GetMapping("/operations")
    public String operations(Model model) {
        model.addAttribute("operations", operationService.findAll());
        return "operations";
    }

    @GetMapping("/operations/search")
    public String operationSearch(@RequestParam("name") String name, Model model){
        model.addAttribute("operations", operationService.searchOperationByName(name));
        return "technologist";
    }

    @GetMapping("/operations/add")
    public String addOperationPage(Model model) {
        model.addAttribute("operation", new Operation());
        return "add_operation";
    }

    @PostMapping("/operations/add")
    public String addOperations(@ModelAttribute("operation") Operation operation, Model model) {
        operationService.addOperation(operation);
        return "redirect:/operations";
    }

    @GetMapping("/operations/{id}")
    public String getOperation(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("operation", operationService.findById(id));
        return "edit_operation";
    }

    @PostMapping("/operations/{id}")
    public String editOperation(@ModelAttribute("operation") Operation operation, Model model) {
        operationService.editOperation(operation);
        return "redirect:/operations";
    }

    @GetMapping("/operations/{id}/delete")
    public String deleteOperation(@PathVariable("id") Integer id, Model model) {
        operationService.deleteOperation(id);
        return "redirect:/operations";
    }


}
