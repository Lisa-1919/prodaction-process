package productionprocess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionprocess.data.entities.OrderOnProduction;
import productionprocess.data.repo.OrderOnProductionDetailsRepo;
import productionprocess.data.repo.OrderOnProductionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderOnProductionService {
    @Autowired
    OrderOnProductionRepo orderOnProductionRepo;

    @Autowired
    OrderOnProductionDetailsRepo orderOnProductionDetailsRepo;

    public List<OrderOnProduction> findAll() {
        return orderOnProductionRepo.findAll();
    }

    public OrderOnProduction findById(int id) {
        return orderOnProductionRepo.findById(id).orElseThrow();
    }

    public void editOrderOnProduction(OrderOnProduction orderOnProduction) {
        OrderOnProduction orderOnProductionDB = orderOnProductionRepo.findById(orderOnProduction.getId()).orElseThrow();
        orderOnProductionDB.setOrderDate(orderOnProduction.getOrderDate());
        orderOnProductionDB.setReceiptDate(orderOnProduction.getReceiptDate());
        orderOnProductionDB.setStatus(orderOnProduction.getStatus());
        orderOnProductionDB.setName(orderOnProduction.getName());
        orderOnProductionDB.setDesiredDate(orderOnProduction.getDesiredDate());
        orderOnProductionDB.setOrderOnProductionDetails(orderOnProduction.getOrderOnProductionDetails());
        orderOnProductionRepo.save(orderOnProductionDB);
    }

    public boolean deleteOrderOnProduction(int id) {
        try{
            OrderOnProduction orderOnProduction = orderOnProductionRepo.findById(id).orElseThrow();
            orderOnProductionRepo.delete(orderOnProduction);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }

    }

    public boolean existsById(int id) {
        return orderOnProductionRepo.existsById(id);
    }

    public void addOrderOnProduction(OrderOnProduction orderOnProduction) {
        orderOnProductionRepo.save(orderOnProduction);
    }

    public List<OrderOnProduction> searchOrderOnProductionByName(String value) {
        Iterable<OrderOnProduction> orderOnProductions = orderOnProductionRepo.findAll();
        List<OrderOnProduction> result = new ArrayList<>();
        for (OrderOnProduction orderOnProduction : orderOnProductions) {
            if (orderOnProduction.getName().toLowerCase().contains(value.toLowerCase())) {
                result.add(orderOnProduction);
            }
        }
        return result;
    }
}
