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
	
	@Column(name = "comments")
	String comments;
	
	@Column(name = "since", columnDefinition = "TIMESTAMP")
	private LocalDateTime since;
	
	@Column(name = "end", columnDefinition = "TIMESTAMP")
	private LocalDateTime end;
	
	@Column(name = "return_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime return_time;
	
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
	
	@JoinTable(name = "eq_lending",
			joinColumns = @JoinColumn(name = "lending_id"),
			inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Equipment> equipmentList;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	public Lending() {
	
	}
	
	public Lending(LocalDateTime since, LocalDateTime end, LocalDateTime return_time, String comments, String lastUpdate) {
		this.since = since;
		this.end = end;
		this.return_time = return_time;
		this.comments = comments;
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
		return "Lending{" +
				"id=" + id +
				", since=" + since +
				", end=" + end +
				", return_time=" + return_time +
				", comments='" + comments + '\'' +
				", user=" + user +
				", event=" + event +
				", equipmentList=" + equipmentList +
				", lastUpdate='" + lastUpdate + '\'' +
				'}';
	}
}
