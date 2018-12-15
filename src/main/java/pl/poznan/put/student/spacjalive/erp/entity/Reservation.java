package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservation", schema = "spacja_erp")
@NamedNativeQueries(
		@NamedNativeQuery(name = "callSelectReservations",
		query = "CALL select_reservations(:dSince, :tSince, :dTo, :tTo)",
		resultClass = Reservation.class)
)
public class Reservation {
	
	//TODO add fields validations
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "comments")
	String comments;
	
	@Column(name = "date_since")
	private String dateSince;
	
	@Column(name = "time_since")
	private String timeSince;
	
	@Column(name = "date_to")
	private String dateTo;
	
	@Column(name = "time_to")
	private String timeTo;
	
	@ManyToOne(cascade = {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name = "event_id")
	private Event event;
	
	@JoinTable(name = "eq_reservation",
			joinColumns = @JoinColumn(name = "reservation_id"),
			inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Equipment> equipmentList;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	public Reservation() {
	
	}
	
	public Reservation(String dateSince, String timeSince, String dateTo, String timeTo) {
		this.dateSince = dateSince;
		this.timeSince = timeSince;
		this.dateTo = dateTo;
		this.timeTo = timeTo;
	}
	
	public Reservation(String comments, String dateSince, String timeSince, String dateTo, String timeTo, User user,
	                   Event event, List<Equipment> equipmentList, String lastUpdate) {
		this.comments = comments;
		this.dateSince = dateSince;
		this.timeSince = timeSince;
		this.dateTo = dateTo;
		this.timeTo = timeTo;
		this.user = user;
		this.event = event;
		this.equipmentList = equipmentList;
		this.lastUpdate = lastUpdate;
	}
	
	public void addEquipment(Equipment equipment) {
		if (equipmentList == null) {
			equipmentList = new ArrayList<>();
			equipmentList.add(equipment);
		} else {
			equipmentList.add(equipment);
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDateSince() {
		return dateSince;
	}
	
	public void setDateSince(String dateSince) {
		this.dateSince = dateSince;
	}
	
	public String getTimeSince() {
		return timeSince;
	}
	
	public void setTimeSince(String timeSince) {
		this.timeSince = timeSince;
	}
	
	public String getDateTo() {
		return dateTo;
	}
	
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	public String getTimeTo() {
		return timeTo;
	}
	
	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
	
	public String getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", comments='" + comments + '\'' +
				", dateSince='" + dateSince + '\'' +
				", timeSince='" + timeSince + '\'' +
				", dateTo='" + dateTo + '\'' +
				", timeTo='" + timeTo + '\'' +
				", user=" + user +
				", event=" + event +
				", equipmentList=" + equipmentList +
				", lastUpdate='" + lastUpdate + '\'' +
				'}';
	}
}
