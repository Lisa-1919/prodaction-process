package productionprocess.data.model;

public enum StatusOrderAtWarehouse {
    STATUS_1("Отправлен"),
    STATUS_2("Получен");

    private String status;

    StatusOrderAtWarehouse() {
    }

    StatusOrderAtWarehouse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusOrderAtWarehouse{" +
                "status='" + status + '\'' +
                '}';
    }
}
