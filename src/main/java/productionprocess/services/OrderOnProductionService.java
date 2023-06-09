package productionprocess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionprocess.data.entities.*;
import productionprocess.data.model.MaterialToOrder;
import productionprocess.data.model.StatusOrderOnProduction;
import productionprocess.data.repo.MaterialRepo;
import productionprocess.data.repo.OrderOnProductionDetailsRepo;
import productionprocess.data.repo.OrderOnProductionRepo;

import java.awt.print.Book;
import java.util.*;

@Service
public class OrderOnProductionService {
    @Autowired
    OrderOnProductionRepo orderOnProductionRepo;

    @Autowired
    OrderOnProductionDetailsRepo orderOnProductionDetailsRepo;

    @Autowired
    MaterialRepo materialRepo;

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

    public void editStatus(int id, StatusOrderOnProduction statusOrderOnProduction) {
        OrderOnProduction orderOnProductionDB = orderOnProductionRepo.findById(id).orElseThrow();
        String status = statusOrderOnProduction.getStatus();
        orderOnProductionDB.setStatus(status);
        orderOnProductionRepo.save(orderOnProductionDB);
    }

    public boolean deleteOrderOnProduction(int id) {
        try {
            OrderOnProduction orderOnProduction = orderOnProductionRepo.findById(id).orElseThrow();
            orderOnProductionRepo.delete(orderOnProduction);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean existsById(int id) {
        return orderOnProductionRepo.existsById(id);
    }

    public void addOrderOnProduction(OrderOnProduction orderOnProduction) {
        orderOnProductionRepo.save(orderOnProduction);
    }

    public List<OrderOnProduction> searchOrderOnProductionById(int value) {
        Iterable<OrderOnProduction> orderOnProductions = orderOnProductionRepo.findAll();
        List<OrderOnProduction> result = new ArrayList<>();
        for (OrderOnProduction orderOnProduction : orderOnProductions) {
            if (orderOnProduction.getId() == value) {
                result.add(orderOnProduction);
            }
        }
        return result;
    }

    public List<MaterialToOrder> getNecessaryQuantity(int orderId) {
        OrderOnProduction orderOnProduction = findById(orderId);
        List<OrderOnProductionDetails> orderOnProductionDetails = orderOnProduction.getOrderOnProductionDetails();
        List<MaterialToOrder> materialToOrders = new ArrayList<>();
        HashMap<String, Double> materialNameAndNecessaryQuantity = new HashMap<>();
        for (OrderOnProductionDetails orderOnProductionDetail : orderOnProductionDetails) {
            Product product = orderOnProductionDetail.getProduct();
            double amount = orderOnProductionDetail.getAmount();
            for (MaterialsForProduct materialsForProduct : product.getMaterialsForProducts()) {
                if (materialNameAndNecessaryQuantity.containsKey(materialsForProduct.getMaterial().getName())) {
                    materialNameAndNecessaryQuantity.put(materialsForProduct.getMaterial().getName(),
                            materialNameAndNecessaryQuantity.get(materialsForProduct.getMaterial().getName())
                                    + materialsForProduct.getQuantity() * amount);
                } else {
                    materialNameAndNecessaryQuantity.put(materialsForProduct.getMaterial().getName(),
                            materialsForProduct.getQuantity() * amount);
                }
            }
        }

        for (Map.Entry<String, Double> map : materialNameAndNecessaryQuantity.entrySet()) {
            String name = map.getKey();
            Material material = materialRepo.findByName(name);
            double stockQuantity = material.getStockQuantity();
            double necessaryQuantity = map.getValue();
            double orderedQuantity = necessaryQuantity - stockQuantity;
            if (orderedQuantity < 0) {
                orderedQuantity = 0;
            }
            materialToOrders.add(new MaterialToOrder(name, stockQuantity, necessaryQuantity, orderedQuantity));
        }
        return materialToOrders;
    }

    public List<OrderOnProduction> getOrderByWorkShop(String workshop) {
        List<OrderOnProduction> orderOnProductions = orderOnProductionRepo.findAll();
        List<OrderOnProduction> result = new ArrayList<>();
        switch (workshop) {
            case "Сборочный цех": {
                for (OrderOnProduction orderOnProduction : orderOnProductions) {
                    if (
//                            orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_1.getStatus()) ||
//                            orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_2.getStatus()) ||
                            orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_3.getStatus()) ||
                                    orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_4.getStatus()) ||
                                    orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_5.getStatus()) ||
                                    orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_6.getStatus())) {
                        result.add(orderOnProduction);
                    }
                }
                break;
            }
            case "Покрасочный цех": {
                for (OrderOnProduction orderOnProduction : orderOnProductions) {
                    if (orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_6.getStatus()) ||
                            orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_7.getStatus()) ||
                            orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_8.getStatus()) ||
                            orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_9.getStatus())) {
                        result.add(orderOnProduction);
                    }
                }
                break;
            }
            case "Упаковочный цех": {
                for (OrderOnProduction orderOnProduction : orderOnProductions) {
                    if (orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_9.getStatus()) ||
                            orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_10.getStatus()) ||
                            orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_11.getStatus()) ||
                            orderOnProduction.getStatus().equals(StatusOrderOnProduction.STATUS_12.getStatus())) {
                        result.add(orderOnProduction);
                    }
                }
                break;
            }
        }
        return result;
    }

    public boolean needPaint(Integer orderId) {
        boolean flag = false;
        OrderOnProduction order = findById(orderId);
        for (OrderOnProductionDetails detail : order.getOrderOnProductionDetails()) {
            if (detail.getProduct().getType().equals("Карандаш")) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void runtime() {
        List<OrderOnProduction> orders = orderOnProductionRepo.findAll();
        for (OrderOnProduction order : orders) {
            if (order.getTotalHours() == 0 && order.getTotalMinutes() == 0) {
                int totalHours = 0;
                int totalMinutes = 0;
                for (OrderOnProductionDetails orderDetail : order.getOrderOnProductionDetails()) {
                    totalHours += orderDetail.getProduct().getRoute().getTotalHours() * orderDetail.getAmount();
                    totalMinutes += orderDetail.getProduct().getRoute().getTotalMinutes() * orderDetail.getAmount();
                }
                if (totalMinutes >= 60) {
                    order.setTotalHours(totalHours + totalMinutes / 60);
                    order.setTotalMinutes(totalMinutes % 60);
                }
            }
        }
        orderOnProductionRepo.saveAll(orders);
    }
}
