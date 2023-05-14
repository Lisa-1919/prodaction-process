package productionprocess.data.entities;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Comparator;

@Entity
@Table(name = "operations")
public class Operation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "hours")
    private int hours;
    @Column(name = "minutes")
    private int minutes;

    @Column(name = "workshop")
    private String workshop;

    public Operation() {
    }

    public Operation(Integer id, String name, String description, int hours, int minutes, String workshop) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.minutes = minutes;
        this.workshop = workshop;
    }

    public Operation(String name, String description, int hours, int minutes, String workshop) {
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.minutes = minutes;
        this.workshop = workshop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Operation)) {
            return false;
        }
        Operation operation = (Operation) obj;
        return id.equals(operation.id);
    }
}
