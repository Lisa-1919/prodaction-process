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
public class AssemblyController {
    @Autowired
    private OrderOnProductionService orderOnProductionService;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/assembly")
    public String assembly(Model model){
        model.addAttribute("ordersOnProduction", orderOnProductionService.getOrderByWorkShop("Сборочный цех"));
        return "assembly_workshop";
    }

    @PostMapping("/assembly")
    public String search(@RequestParam("id") Integer id, Model model){
        model.addAttribute("ordersOnProduction", orderOnProductionService.searchOrderOnProductionById(id));
        return "assembly_workshop";
    }

    @GetMapping("/assembly/{id}")
    public String order(@RequestParam("id") Integer id, Model model){
        model.addAttribute("orderOnProduction", orderOnProductionService.findById(id));
        model.addAttribute("userRole", "ASSEMBLY_SHOP_MASTER");
        //model.addAttribute("userRole", employeeService.getAuthorized().getRoleName());
        return "order_on_production";
    }

    @GetMapping("/assembly/end")
    public String assemblyEnd(Model model){
        return "redirect:/assembly";
    }
}
