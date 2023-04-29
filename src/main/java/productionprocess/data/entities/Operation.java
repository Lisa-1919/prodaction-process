package productionprocess.data.entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "workshop")
    private String workshop;

    public Operation() {
    }

    public Operation(Integer id, String name, String description, LocalTime time, String workshop) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.workshop = workshop;
    }

    public Operation(String name, String description, LocalTime time, String workshop) {
        this.name = name;
        this.description = description;
        this.time = time;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
