package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import productionprocess.services.EmployeeService;
import productionprocess.services.OrderOnProductionService;

@Controller
public class PaintController {
    @Autowired
    private OrderOnProductionService orderOnProductionService;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/paint")
    public String paint(Model model){
        model.addAttribute("orders", orderOnProductionService.findAll());
        return "paint_workshop";
    }

    @PostMapping("/paint")
    public String search(@RequestParam("id") Integer id, Model model){
        model.addAttribute("orders", orderOnProductionService.searchOrderOnProductionById(id));
        return "paint_workshop";
    }

    @GetMapping("/paint/{id}")
    public String order(@RequestParam("id") Integer id, Model model){
        model.addAttribute("orderOnProduction", orderOnProductionService.findById(id));
        model.addAttribute("userRole", employeeService.getAuthorized().getRoleName());
        return "order_on_production";
    }

    @GetMapping("/paint/end")
    public String paintEnd(Model model){
        return "redirect:/paint";
    }
}
