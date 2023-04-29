package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import productionprocess.services.ProductService;

@Controller
public class TechnologistController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products(Model model){
        return "technologist";
    }
}
