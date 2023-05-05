package productionprocess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import productionprocess.data.entities.OrderAtWarehouse;
import productionprocess.data.entities.OrderAtWarehouseDetails;
import productionprocess.data.entities.OrderOnProduction;
import productionprocess.data.model.MaterialToOrder;
import productionprocess.data.model.StatusOrderAtWarehouse;
import productionprocess.data.model.StatusOrderOnProduction;
import productionprocess.services.MaterialService;
import productionprocess.services.OrderAtWarehouseService;
import productionprocess.services.OrderOnProductionService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DispatcherController {
    @Autowired
    private OrderOnProductionService orderOnProductionService;
    @Autowired
    private OrderAtWarehouseService orderAtWarehouseService;
    @Autowired
    private MaterialService materialService;

    @GetMapping("/orders-w")
    public String OrdersAtWarehouse(Model model){
        model.addAttribute("ordersAtWarehouse", orderAtWarehouseService.findAll());
        return "warehouse_order_history";
    }

    @GetMapping("/orders-w/{id}")
    public String confirmReceiptOfOrder(@PathVariable("id") Integer orderId, Model model){
        orderAtWarehouseService.confirmReceipt(orderId, StatusOrderAtWarehouse.STATUS_2);
        return "redirect:/orders-w";
    }

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

    @GetMapping("/orders-p/{id}/start")
    public String start(@PathVariable("id") Integer id, Model model){
        orderOnProductionService.editStatus(id, StatusOrderOnProduction.STATUS_3);
        return "redirect:/orders-p";
    }

    @GetMapping("/orders-p/{id}/components")
    public String orderOnProductionComponents(@PathVariable("id") Integer id, Model model){
        List<MaterialToOrder> materialToOrderList = orderOnProductionService.getNecessaryQuantity(id);
        model.addAttribute("materialsToOrder", materialToOrderList);
        //model.addAttribute("orderAtWarehouse", new OrderAtWarehouse());
        return "order-w";
    }

    @PostMapping("/orders-p/{id}/components")
    public String createOrderAtWarehouse(@PathVariable("id") Integer id, Model model){
        List<MaterialToOrder> materialToOrder = orderOnProductionService.getNecessaryQuantity(id);
        OrderAtWarehouse orderAtWarehouse = new OrderAtWarehouse();
        orderAtWarehouse.setOrderDate(LocalDateTime.now());
        orderAtWarehouse.setStatus(StatusOrderAtWarehouse.STATUS_1.getStatus());
        orderOnProductionService.editStatus(id, StatusOrderOnProduction.STATUS_2);
        for(MaterialToOrder material: materialToOrder){
            if(material.getOrderedQuantity() > 0) {
                OrderAtWarehouseDetails orderAtWarehouseDetail = new OrderAtWarehouseDetails();
                orderAtWarehouseDetail.setMaterial(materialService.findByName(material.getName()));
                orderAtWarehouseDetail.setAmount(material.getOrderedQuantity());
                orderAtWarehouse.getOrderAtWarehouseDetails().add(orderAtWarehouseDetail);
            }
        }

        orderAtWarehouseService.addOrderAtWarehouse(orderAtWarehouse);
        return "redirect:/orders-p";
    }

}
