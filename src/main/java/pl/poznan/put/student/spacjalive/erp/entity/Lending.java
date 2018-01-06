package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "lending")
public class Lending {

    //TODO add fields validations


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "since", columnDefinition = "TIMESTAMP")
    private LocalDateTime since;

    @Column(name = "end", columnDefinition = "TIMESTAMP")
    private LocalDateTime end;

    @Column(name = "return_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime return_time;

    @Column(name = "comments")
    String comments;

    @ManyToOne(cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "event_id")
    private Event event;

    @JoinTable(name = "eq_lending",
            joinColumns = @JoinColumn(name = "lending_id"),
            inverseJoinColumns = @JoinColumn(name="equipment_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Equipment> equipmentList;

    public void addEquipment(Equipment equipment) {
        if(equipmentList == null) {
            equipmentList = new ArrayList<>();
            equipmentList.add(equipment);
        } else {
            equipmentList.add(equipment);
        }
    }

    public Lending() {

    }

    public Lending(LocalDateTime since, LocalDateTime end, LocalDateTime return_time, String comments) {
        this.since = since;
        this.end = end;
        this.return_time = return_time;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getSince() {
        return since;
    }

    public void setSince(LocalDateTime since) {
        this.since = since;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalDateTime getReturn_time() {
        return return_time;
    }

    public void setReturn_time(LocalDateTime return_time) {
        this.return_time = return_time;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Override
    public String toString() {
        return "Lending{" +
                "id=" + id +
                ", since=" + since +
                ", end=" + end +
                ", return_time=" + return_time +
                ", comments='" + comments + '\'' +
                ", employee=" + employee +
                ", event=" + event +
                ", equipmentList=" + equipmentList +
                '}';
    }
}
