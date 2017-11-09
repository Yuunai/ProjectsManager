package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Participation.class)
public class Participation implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Participation() {

    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "event=" + event +
                ", employee=" + employee +
                ", role=" + role +
                '}';
    }
}
