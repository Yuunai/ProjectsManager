package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "position")
public class Position {

    //TODO add fields validations

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "lending_time")
    private int lendingTime;

    public Position() {
    }

    public Position(String name, int lendingTime) {
        this.name = name;
        this.lendingTime = lendingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(int lendingTime) {
        this.lendingTime = lendingTime;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lendingTime=" + lendingTime +
                '}';
    }
}
