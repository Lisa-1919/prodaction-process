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
public class PackingController {
    @Autowired
    private OrderOnProductionService orderOnProductionService;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/packing")
    public String packing(Model model){
        model.addAttribute("ordersOnProduction", orderOnProductionService.getOrderByWorkShop("Упаковочный цех"));
        return "packing_workshop";
    }

    @GetMapping("/packing/search")
    public String search(@RequestParam("id") Integer id, Model model){
        model.addAttribute("ordersOnProduction", orderOnProductionService.searchOrderOnProductionById(id));
        return "packing_workshop";
    }

    @GetMapping("/packing/{id}")
    public String order(@PathVariable("id") Integer id, Model model){
        model.addAttribute("orderOnProduction", orderOnProductionService.findById(id));
        model.addAttribute("userRole", "PACKING_SHOP_MASTER");
      //  model.addAttribute("userRole", employeeService.getAuthorized().getRoleName());
        return "order_on_production";
    }

    @GetMapping("/packing/{id}/start")
    public String startPacking(@PathVariable("id") Integer orderId, Model model) {
        orderOnProductionService.editStatus(orderId, StatusOrderOnProduction.STATUS_10);
        return "redirect:/packing";
    }

    @GetMapping("/packing/{id}/end")
    public String packingEnd(@PathVariable("id") Integer orderId, Model model) {
        orderOnProductionService.editStatus(orderId, StatusOrderOnProduction.STATUS_11);
        return "redirect:/packing";
    }

    @GetMapping("/packing/{id}/send")
    public String orderSend(@PathVariable("id") Integer orderId, Model model) {
        orderOnProductionService.editStatus(orderId, StatusOrderOnProduction.STATUS_12);
        return "redirect:/packing";
    }
}
