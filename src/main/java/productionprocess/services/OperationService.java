package productionprocess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionprocess.data.entities.Operation;
import productionprocess.data.repo.OperationInRouteRepo;
import productionprocess.data.repo.OperationRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OperationService {
    @Autowired
    OperationRepo operationRepo;

    @Autowired
    OperationInRouteRepo operationInRouteRepo;

    public List<Operation> findAll() {
        return operationRepo.findAll();
    }

    public Operation findById(int id) {
        return operationRepo.findById(id).orElseThrow();
    }

    public void editOperation(Operation operation) {
        Operation operationDB = operationRepo.findById(operation.getId()).orElseThrow();
        operationDB.setName(operation.getName());
        operationDB.setDescription(operation.getDescription());
        operationDB.setHours(operation.getHours());
        operationDB.setMinutes(operation.getMinutes());
        operationRepo.save(operationDB);
    }

    public boolean deleteOperation(int id) {
        try{
            Operation operation = operationRepo.findById(id).orElseThrow();
            operationRepo.delete(operation);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }

    }

    public boolean existsById(int id) {
        return operationRepo.existsById(id);
    }

    public void addOperation(Operation operation) {
        operationRepo.save(operation);
    }

    public List<Operation> searchOperationByName(String value) {
        Iterable<Operation> operations = operationRepo.findAll();
        List<Operation> result = new ArrayList<>();
        for (Operation operation : operations) {
            if (operation.getName().toLowerCase().contains(value.toLowerCase())) {
                result.add(operation);
            }
        }
        return result;
    }
}
