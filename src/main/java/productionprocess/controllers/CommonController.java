package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import productionprocess.services.EmployeeService;

@Controller
public class CommonController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/home")
    public String homePage(Model model) {
        switch (employeeService.getAuthorized().getRoleName()) {
            case "ADMIN" -> {
                return "redirect:/employees";
            }
            case "OPERATOR" -> {
                return "redirect:/orders-p";
            }
            case "TECHNOLOGIST" -> {
                return "redirect:/products";
            }
            case "ASSEMBLY_SHOP_MASTER" -> {
                return "redirect:/assembly";
            }
            case "PAINT_SHOP_MASTER" ->{
                return "redirect:/paint";
            }
            case "PACKING_SHOP_MASTER" ->{
                return "redirect:/packing";
            }
            default -> {
                return "redirect:/login";
            }
        }
    }
}
