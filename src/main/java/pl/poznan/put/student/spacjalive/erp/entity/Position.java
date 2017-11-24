package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
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

    @Column(name = "lending_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime lendingTime;

//    @OneToMany(cascade = {CascadeType.DETACH,
//                    CascadeType.MERGE,
//                    CascadeType.PERSIST,
//                    CascadeType.REFRESH})
//    @JoinColumn(name = "position_id")
//    private List<Employee> employees;

    public Position() {
    }

    public Position(String name, LocalDateTime lendingTime) {
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

    public LocalDateTime getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(LocalDateTime lendingTime) {
        this.lendingTime = lendingTime;
    }

//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lendingTime=" + lendingTime +
//                ", employees=" + employees +
                '}';
    }
}
