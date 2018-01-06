package pl.poznan.put.student.spacjalive.erp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event {

    //TODO add fields validations

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Pole nie może być puste!")
    @Size(min = 1, message = "Pole nie może być puste!")
    @Size(max = 60, message = "Długość pola nie może przekroczyć 60 znaków!")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Pole nie może być puste!")
    @Size(min = 1, message = "Pole nie może być puste!")
    @Size(max = 45, message = "Długość pola nie może przekroczyć 45 znaków!")
    @Column(name = "place")
    private String place;

    @NotNull(message = "Wymagany format pola: 'dd.MM.yyyy hh:mm!' Nie może być puste!")
    @FutureOrPresent(message = "Nie można dodać wydarzenia z przeszłości!")
    @DateTimeFormat(pattern = "d.M.u H:m")
    @Column(name = "date", columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @NotNull(message = "Pole nie może być puste!")
    @Size(min = 1, message = "Pole nie może być puste!")
    @Size(max = 60, message = "Długość pola nie może przekroczyć 60 znaków!")
    @Column(name = "organizer")
    private String organizer;

    @NotNull(message = "Pole nie może być puste!")
    @Size(min = 1, message = "Pole nie może być puste!")
    @Size(max = 20, message = "Długość pola nie może przekroczyć 20 znaków!")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "Pole nie może być puste!")
    @Size(min = 1, message = "Pole nie może być puste!")
    @Size(max = 40, message = "Długość pola nie może przekroczyć 40 znaków!")
    @Email(message = "Niepoprawny email!")
    @Column(name = "email")
    private String email;

    @Size(max = 256, message = "Długość pola nie może przekroczyć 256 znaków!")
    @Column(name = "comments")
    private String comments;

    @Min(value = 0, message = "Wybierz liczbę z zakresu 0-10!")
    @Max(value = 10, message = "Wybierz liczbę z zakresu 0-10")
    @Digits(integer = 2, fraction = 0, message = "Dozwolone wyłącznie liczby całkowite!")
    @Column(name = "priority")
    private int priority;

    @NotNull(message = "Wymagany format pola: 'dd.MM.yyyy hh:mm!' Nie może być puste!")
    @FutureOrPresent(message = "Nie można dodać wydarzenia z przeszłości!")
    @DateTimeFormat(pattern = "d.M.u H:m")
    @Column(name = "deadline", columnDefinition = "TIMESTAMP")
    private LocalDateTime deadline;

    @Column(name = "archived")
    private int archived;

    @NotNull(message = "Pole nie może być puste!")
    @Size(min = 1, message = "Pole nie może być puste!")
    @Size(max = 40, message = "Długość pola nie może przekroczyć 40 znaków!")
    @Column(name = "video_type")
    private String videoType;

    @Min(value = 0, message = "Wybierz liczbę większą lub równą 0!")
    @Digits(integer = 5, fraction = 2, message = "Wyłącznie liczby!")
    @Column(name = "value")
    private double value;

    public Event() {

    }

    public Event(String name, String place, LocalDateTime date, String organizer, String phoneNumber, String email, String comments, int priority, LocalDateTime deadline, int archived, String videoType, double value) {
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

    public int isArchived() {
        return archived;
    }

    public void setArchived(int archived) {
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
                '}';
    }
}
