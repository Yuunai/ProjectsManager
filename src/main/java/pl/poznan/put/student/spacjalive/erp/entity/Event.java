package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "place")
    private String place;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "organizer")
    private String organizer;

    @Column(name = "contact")
    private String contact;

    @Column(name = "comments")
    private String comments;

    @Column(name = "priority")
    private int priority;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "archived")
    private boolean archived;

    @Column(name = "video_type")
    private String videoType;

    @Column(name = "value")
    private double value;

    @OneToMany(mappedBy = "event")
    private List<Participation> participations;

    public Event() {

    }

    public Event(String name, String place, Date date, String organizer, String contact, String comments, int priority, Date deadline, boolean archived, String videoType, double value) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.organizer = organizer;
        this.contact = contact;
        this.comments = comments;
        this.priority = priority;
        this.deadline = deadline;
        this.archived = archived;
        this.videoType = videoType;
        this.value = value;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public void addParticipation(Participation participation) {
        if(participations == null) {
            participations = new ArrayList<>();
            participations.add(participation);
        } else {
            participations.add(participation);
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", date=" + date +
                ", organizer='" + organizer + '\'' +
                ", contact='" + contact + '\'' +
                ", comments='" + comments + '\'' +
                ", priority=" + priority +
                ", deadline=" + deadline +
                ", archived=" + archived +
                ", videoType='" + videoType + '\'' +
                ", value=" + value +
                ", participations=" + participations +
                '}';
    }
}
