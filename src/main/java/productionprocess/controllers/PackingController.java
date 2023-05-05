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
public class PackingController {
    @Autowired
    private OrderOnProductionService orderOnProductionService;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/packing")
    public String packing(Model model){
        model.addAttribute("orders", orderOnProductionService.findAll());
        return "packing_workshop";
    }

    @PostMapping("/packing")
    public String search(@RequestParam("id") Integer id, Model model){
        model.addAttribute("orders", orderOnProductionService.searchOrderOnProductionById(id));
        return "packing_workshop";
    }

    @GetMapping("/packing/{id}")
    public String order(@RequestParam("id") Integer id, Model model){
        model.addAttribute("orderOnProduction", orderOnProductionService.findById(id));
        model.addAttribute("userRole", employeeService.getAuthorized().getRoleName());
        return "order_on_production";
    }

    @GetMapping("/packing/end")
    public String assemblyEnd(Model model){
        return "redirect:/packing";
    }
}
