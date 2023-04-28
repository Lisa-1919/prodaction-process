package productionprocess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionprocess.data.entities.OrderAtWarehouse;
import productionprocess.data.repo.OrderAtWarehouseDetailsRepo;
import productionprocess.data.repo.OrderAtWarehouseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderAtWarehouseService {
    @Autowired
    OrderAtWarehouseRepo orderAtWarehouseRepo;

    @Autowired
    OrderAtWarehouseDetailsRepo orderAtWarehouseDetailsRepo;

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

    public boolean deleteOrderAtWarehouse(int id) {
        try{
            OrderAtWarehouse orderAtWarehouse = orderAtWarehouseRepo.findById(id).orElseThrow();
            orderAtWarehouseRepo.delete(orderAtWarehouse);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }

    }

    public boolean existsById(int id) {
        return orderAtWarehouseRepo.existsById(id);
    }

    public void addOrderAtWarehouse(OrderAtWarehouse orderAtWarehouse) {
        orderAtWarehouseRepo.save(orderAtWarehouse);
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
}
