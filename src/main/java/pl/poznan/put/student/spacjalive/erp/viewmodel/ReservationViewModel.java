package pl.poznan.put.student.spacjalive.erp.viewmodel;

import pl.poznan.put.student.spacjalive.erp.entity.*;

import java.util.List;

public class ReservationViewModel {
//	TODO validation
	private int id;
	
	private String comments;
	
	private String dateSince;
	
	private String timeSince;
	
	private String dateTo;
	
	private String timeTo;
	
	private Integer userId;
	
	private Integer eventId;
	
	private List<Equipment> equipmentList;
	
	private String lastUpdate;
	
	public ReservationViewModel() {
	}
	
	public ReservationViewModel(int id, String comments, String dateSince, String timeSince, String dateTo,
	                            String timeTo, int userId, int eventId, List<Equipment> equipmentList,
	                            String lastUpdate) {
		this.id = id;
		this.comments = comments;
		this.dateSince = dateSince;
		this.timeSince = timeSince;
		this.dateTo = dateTo;
		this.timeTo = timeTo;
		this.userId = userId;
		this.eventId = eventId;
		this.equipmentList = equipmentList;
		this.lastUpdate = lastUpdate;
	}
	
	public ReservationViewModel(Reservation reservation) {
		this.id = reservation.getId();
		this.comments = reservation.getComments();
		this.dateSince = reservation.getDateSince();
		this.timeSince = reservation.getTimeSince();
		this.dateTo = reservation.getDateTo();
		this.timeTo = reservation.getTimeTo();
		this.userId = reservation.getUser() != null ? reservation.getUser().getId() : null;
		this.eventId = reservation.getEvent() != null ? reservation.getEvent().getId() : null;
		this.equipmentList = reservation.getEquipmentList();
		this.lastUpdate = reservation.getLastUpdate();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
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
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getEventId() {
		return eventId;
	}
	
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
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
		return "ReservationViewModel{" +
				"id=" + id +
				", comments='" + comments + '\'' +
				", dateSince='" + dateSince + '\'' +
				", timeSince='" + timeSince + '\'' +
				", dateTo='" + dateTo + '\'' +
				", timeTo='" + timeTo + '\'' +
				", userId=" + userId +
				", eventId=" + eventId +
				", equipmentList=" + equipmentList +
				", lastUpdate='" + lastUpdate + '\'' +
				'}';
	}
}
