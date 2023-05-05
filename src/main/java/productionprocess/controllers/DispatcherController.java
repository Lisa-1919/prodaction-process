package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import productionprocess.data.entities.OrderAtWarehouse;
import productionprocess.data.model.MaterialToOrder;
import productionprocess.services.OrderAtWarehouseService;
import productionprocess.services.OrderOnProductionService;

import java.util.List;

@Controller
public class DispatcherController {
    @Autowired
    private OrderOnProductionService orderOnProductionService;
    @Autowired
    private OrderAtWarehouseService orderAtWarehouseService;

    @GetMapping("/orders-p")
    public String ordersOnProduction(Model model){
        model.addAttribute("ordersOnProduction", orderOnProductionService.findAll());
        return "dispatcher";
    }

    @PostMapping("/orders-p")
    public String search(@RequestParam("id") Integer id, Model model){
        model.addAttribute("orderOnProduction",orderOnProductionService.searchOrderOnProductionById(id));
        return "dispatcher";
    }

    @GetMapping("/orders-p/{id}")
    public String order(@PathVariable("id") Integer id, Model model){
        model.addAttribute("orderOnProduction", orderOnProductionService.findById(id));
        return "order-p";
    }

    @GetMapping("/order/{id}/start")
    public String start(@PathVariable("id") Integer id, Model model){
        return "redirect:/orders-p";
    }

    @GetMapping("/orders-p/{id}/components")
    public String orderOnProductionComponents(@PathVariable("id") Integer id, Model model){
        List<MaterialToOrder> materialToOrderList = orderOnProductionService.getNecessaryQuantity(id);
        model.addAttribute("materialsToOrder", materialToOrderList);
        //model.addAttribute("orderAtWarehouse", new OrderAtWarehouse());
        return "order-w";
    }

    @PostMapping("/orders-w")
    public String createOrderAtWarehouse(Model model){
        return "redirect:/orders-p";
    }

}
