package productionprocess.data.model;

import productionprocess.data.entities.Operation;

import java.util.Comparator;

public class OperationComparator implements Comparator<Operation> {
    @Override
    public int compare(Operation o1, Operation o2) {
        return o1.getWorkshop().toLowerCase().compareTo(o2.getWorkshop().toLowerCase());
    }
}
