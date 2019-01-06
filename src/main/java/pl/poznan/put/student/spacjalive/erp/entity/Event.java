package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "event")
public class Event implements Serializable {
	
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
	
	@NotNull(message = "Pole nie może być puste!")
	@Column(name = "date", columnDefinition = "DATE")
	private String date;
	
	@NotNull(message = "Pole nie może być puste!")
	@Column(name = "time", columnDefinition = "TIME")
	private String time;
	
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
	
	@NotNull(message = "Wymagany format pola: 'yyyy.MM.dd!' Nie może być puste!")
	@Column(name = "deadline", columnDefinition = "DATE")
	private String deadline;
	
	@Column(name = "published")
	private boolean published;
	
	@Column(name = "archived")
	private boolean archived;
	
	@NotNull(message = "Pole nie może być puste!")
	@Size(min = 1, message = "Pole nie może być puste!")
	@Size(max = 40, message = "Długość pola nie może przekroczyć 40 znaków!")
	@Column(name = "video_type")
	private String videoType;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	public Event() {
	
	}
	
	public Event(String name, String place, String date, String time, String organizer, String phoneNumber,
	             String email, String comments, int priority, String deadline, boolean published, boolean archived,
	             String videoType, String lastUpdate) {
		this.name = name;
		this.place = place;
		this.date = date;
		this.time = time;
		this.organizer = organizer;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.comments = comments;
		this.priority = priority;
		this.deadline = deadline;
		this.published = published;
		this.archived = archived;
		this.videoType = videoType;
		this.lastUpdate = lastUpdate;
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
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
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
	
	public String getDeadline() {
		return deadline;
	}
	
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	public boolean isPublished() {
		return published;
	}
	
	public void setPublished(boolean published) {
		this.published = published;
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
	
	public String getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "Event{" +
				"id=" + id +
				", name='" + name + '\'' +
				", place='" + place + '\'' +
				", date='" + date + '\'' +
				", time='" + time + '\'' +
				", organizer='" + organizer + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				", comments='" + comments + '\'' +
				", priority=" + priority +
				", deadline='" + deadline + '\'' +
				", published=" + published +
				", archived=" + archived +
				", videoType='" + videoType + '\'' +
				", lastUpdate='" + lastUpdate + '\'' +
				'}';
	}
}
