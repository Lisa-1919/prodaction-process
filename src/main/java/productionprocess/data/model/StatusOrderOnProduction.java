package productionprocess.data.model;

public enum StatusOrderOnProduction {
    STATUS_1("Ожидание выполнения"),
    STATUS_2("Ожидание комплектующих"),
    STATUS_3("Отправлен на сборку"),
    STATUS_4("Сборка"),
    STATUS_5("Сборка завершена"),
    STATUS_6("Отправлен на покраску"),
    STATUS_7("Покраска"),
    STATUS_8("Покраска завершена"),
    STATUS_9("Отправлен на упаковку"),
    STATUS_10("Упаковка"),
    STATUS_11("Упаковка завершена"),
    STATUS_12("Отправлен на склад");

    private String status;

    StatusOrderOnProduction() {
    }

    StatusOrderOnProduction(String status) {
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
        return "StatusOrderOnProduction{" +
                "status='" + status + '\'' +
                '}';
    }
}
