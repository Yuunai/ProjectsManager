package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "participation")
@IdClass(Participation.class)
public class Participation implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	public Participation() {
	
	}
	
	public Participation(Event event, User user, Role role) {
		this.event = event;
		this.user = user;
		this.role = role;
	}
	
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
				", user=" + user +
				", role=" + role +
				'}';
	}
}
