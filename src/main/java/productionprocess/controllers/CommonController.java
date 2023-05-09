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
            case "Администратор" -> {
                return "redirect:/employees";
            }
            case "Диспетчер" -> {
                return "redirect:/orders-p";
            }
            case "Технолог" -> {
                return "redirect:/products";
            }
            case "Мастер сборочного цеха" -> {
                return "redirect:/assembly";
            }
            case "Мастер покрасочного цеха" -> {
                return "redirect:/paint";
            }
            case "Мастер упаковочного цеха" -> {
                return "redirect:/packing";
            }
            default -> {
                return "redirect:/login";
            }
        }
    }
}
