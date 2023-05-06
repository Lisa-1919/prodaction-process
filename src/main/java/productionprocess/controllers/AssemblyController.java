package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import productionprocess.data.model.StatusOrderOnProduction;
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

    @GetMapping("/assembly/search")
    public String search(@RequestParam("id") Integer id, Model model){
        model.addAttribute("ordersOnProduction", orderOnProductionService.searchOrderOnProductionById(id));
        return "assembly_workshop";
    }

    @GetMapping("/assembly/{id}")
    public String orderPage(@PathVariable("id") Integer id, Model model){
        model.addAttribute("orderOnProduction", orderOnProductionService.findById(id));
        model.addAttribute("userRole", "ASSEMBLY_SHOP_MASTER");
        //model.addAttribute("userRole", employeeService.getAuthorized().getRoleName());
        return "order_on_production";
    }

    @GetMapping("/assembly/{id}/start")
    public String startAssembly(@PathVariable("id") Integer orderId, Model model){
        orderOnProductionService.editStatus(orderId, StatusOrderOnProduction.STATUS_4);
        return "redirect:/assembly";
    }

    @GetMapping("/assembly/{id}/end")
    public String assemblyEnd(@PathVariable("id") Integer orderId, Model model){
        orderOnProductionService.editStatus(orderId, StatusOrderOnProduction.STATUS_5);
        return "redirect:/assembly";
    }

    @GetMapping("/assembly/{id}/send")
    public String orderSend(@PathVariable("id") Integer orderId, Model model){
        if(orderOnProductionService.needPaint(orderId)){
            orderOnProductionService.editStatus(orderId, StatusOrderOnProduction.STATUS_6);
        }else {
            orderOnProductionService.editStatus(orderId, StatusOrderOnProduction.STATUS_9);
        }
        return "redirect:/assembly";
    }
}
