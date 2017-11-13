package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "lending")
public class Lending {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "since")
    private Date since;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "to")
    private Date to;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_time")
    private Date return_time;

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

    public Lending() {

    }

    public Lending(Date since, Date to, Date return_time, String comments) {
        this.since = since;
        this.to = to;
        this.return_time = return_time;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Date getReturn_time() {
        return return_time;
    }

    public void setReturn_time(Date return_time) {
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

    @Override
    public String toString() {
        return "Lending{" +
                "id=" + id +
                ", since=" + since +
                ", to=" + to +
                ", return_time=" + return_time +
                ", comments='" + comments + '\'' +
                ", employee=" + employee +
                ", event=" + event +
                '}';
    }
}
