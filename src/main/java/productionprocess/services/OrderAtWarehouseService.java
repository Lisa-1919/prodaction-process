package productionprocess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionprocess.data.entities.Material;
import productionprocess.data.entities.OrderAtWarehouse;
import productionprocess.data.entities.OrderAtWarehouseDetails;
import productionprocess.data.entities.OrderOnProduction;
import productionprocess.data.model.StatusOrderAtWarehouse;
import productionprocess.data.repo.MaterialRepo;
import productionprocess.data.repo.OrderAtWarehouseDetailsRepo;
import productionprocess.data.repo.OrderAtWarehouseRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderAtWarehouseService {
    @Autowired
    private OrderAtWarehouseRepo orderAtWarehouseRepo;
    @Autowired
    private OrderAtWarehouseDetailsRepo orderAtWarehouseDetailsRepo;
    @Autowired
    private MaterialService materialService;

    public List<OrderAtWarehouse> findAll() {
        return orderAtWarehouseRepo.findAll();
    }

    public OrderAtWarehouse findById(int id) {
        return orderAtWarehouseRepo.findById(id).orElseThrow();
    }

    public void editOrderAtWarehouse(OrderAtWarehouse orderAtWarehouse) {
        OrderAtWarehouse orderAtWarehouseDB = orderAtWarehouseRepo.findById(orderAtWarehouse.getId()).orElseThrow();
        orderAtWarehouseDB.setOrderDate(orderAtWarehouse.getOrderDate());
        orderAtWarehouseDB.setReceiptDate(orderAtWarehouse.getReceiptDate());
        orderAtWarehouseDB.setStatus(orderAtWarehouseDB.getStatus());
        orderAtWarehouseDB.setOrderAtWarehouseDetails(orderAtWarehouse.getOrderAtWarehouseDetails());
        orderAtWarehouseRepo.save(orderAtWarehouseDB);
    }

    public void confirmReceipt(int id, StatusOrderAtWarehouse statusOrderAtWarehouse) {
        OrderAtWarehouse orderAtWarehouseDB = orderAtWarehouseRepo.findById(id).orElseThrow();
        String status = statusOrderAtWarehouse.getStatus();
        orderAtWarehouseDB.setStatus(status);
        orderAtWarehouseDB.setReceiptDate(LocalDateTime.now());
        for(OrderAtWarehouseDetails details: orderAtWarehouseDB.getOrderAtWarehouseDetails()){
            Material material = materialService.findById(details.getMaterial().getId());
            material.setStockQuantity(material.getStockQuantity() + details.getAmount());
            materialService.editMaterial(material);
        }
        orderAtWarehouseRepo.save(orderAtWarehouseDB);
    }

    public boolean deleteOrderAtWarehouse(int id) {
        try {
            OrderAtWarehouse orderAtWarehouse = orderAtWarehouseRepo.findById(id).orElseThrow();
            orderAtWarehouseRepo.delete(orderAtWarehouse);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean existsById(int id) {
        return orderAtWarehouseRepo.existsById(id);
    }

    public void addOrderAtWarehouse(OrderAtWarehouse orderAtWarehouse) {
        orderAtWarehouseRepo.save(orderAtWarehouse);
        orderAtWarehouse.getOrderAtWarehouseDetails().forEach(detail -> {
            if (detail.getAmount() % 100 != 0) {
                double amount = detail.getAmount() + (100 - detail.getAmount() % 100);
                detail.setAmount(amount);
            }
            detail.setOrderAtWarehouse(orderAtWarehouse);
        });
        orderAtWarehouseDetailsRepo.saveAll(orderAtWarehouse.getOrderAtWarehouseDetails());
    }

    public List<OrderAtWarehouse> searchOrderAtWarehouseByStatus(String value) {
        Iterable<OrderAtWarehouse> orderAtWarehouses = orderAtWarehouseRepo.findAll();
        List<OrderAtWarehouse> result = new ArrayList<>();
        for (OrderAtWarehouse orderAtWarehouse : orderAtWarehouses) {
            if (orderAtWarehouse.getStatus().toLowerCase().contains(value.toLowerCase())) {
                result.add(orderAtWarehouse);
            }
        }
        return result;
    }
    public List<OrderAtWarehouse> searchOrderAtWarehouseById(int value) {
        Iterable<OrderAtWarehouse> ordersAtWarehouse = orderAtWarehouseRepo.findAll();
        List<OrderAtWarehouse> result = new ArrayList<>();
        for (OrderAtWarehouse orderAtWarehouse : ordersAtWarehouse) {
            if (orderAtWarehouse.getId() == value) {
                result.add(orderAtWarehouse);
            }
        }
        return result;
    }
}
