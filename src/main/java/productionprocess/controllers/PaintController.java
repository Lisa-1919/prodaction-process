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
public class PaintController {
    @Autowired
    private OrderOnProductionService orderOnProductionService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/paint")
    public String paint(Model model) {
        model.addAttribute("ordersOnProduction", orderOnProductionService.getOrderByWorkShop("Покрасочный цех"));
        model.addAttribute("userRole", employeeService.getAuthorized().getRoleName());
        return "paint_workshop";
    }

    @GetMapping("/paint/search")
    public String search(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("ordersOnProduction", orderOnProductionService.searchOrderOnProductionById(id));
        return "paint_workshop";
    }

    @GetMapping("/paint/{id}")
    public String order(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("orderOnProduction", orderOnProductionService.findById(id));
        model.addAttribute("userRole", "PAINT_SHOP_MASTER");
        //model.addAttribute("userRole", employeeService.getAuthorized().getRoleName());
        return "order_on_production";
    }

    @GetMapping("/paint/{id}/start")
    public String startPaint(@PathVariable("id") Integer orderId, Model model) {
        orderOnProductionService.editStatus(orderId, StatusOrderOnProduction.STATUS_7);
        return "redirect:/paint";
    }

    @GetMapping("/paint/{id}/end")
    public String assemblyEnd(@PathVariable("id") Integer orderId, Model model) {
        orderOnProductionService.editStatus(orderId, StatusOrderOnProduction.STATUS_8);
        return "redirect:/paint";
    }

    @GetMapping("/paint/{id}/send")
    public String orderSend(@PathVariable("id") Integer orderId, Model model) {
        orderOnProductionService.editStatus(orderId, StatusOrderOnProduction.STATUS_9);
        return "redirect:/paint";
    }
}
