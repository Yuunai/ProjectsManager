package pl.poznan.put.student.spacjalive.erp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    //TODO add fields validations

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "place")
    private String place;

    @DateTimeFormat(pattern = "d.M.u H:m")
//    @Pattern(regexp = "^[0-9]{2}\\-((0[0-9])|(1[0-2]))\\-((0[1-9])|([1-2][0-9])|(3[0-1]))\\ (([0-1][0-9])|(2[0-3]))\\:[0-5][0-9]",
//            message = "Niepoprawny format daty! YY-MM-DD hh:mm")
    @Column(name = "date", columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @Column(name = "organizer")
    private String organizer;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "comments")
    private String comments;

    @Column(name = "priority")
    private int priority;

    @DateTimeFormat(pattern = "d.M.u H:m")
    @Column(name = "deadline", columnDefinition = "TIMESTAMP")
    private LocalDateTime deadline;

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

    public Event(String name, String place, LocalDateTime date, String organizer, String phoneNumber, String email, String comments, int priority, LocalDateTime deadline, boolean archived, String videoType, double value) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.organizer = organizer;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
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
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
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
