package pl.poznan.put.student.spacjalive.erp.viewmodel;

public class ParticipationViewModel {
	
	private int eventId;
	private int userId;
	private int roleId;
	
	public int getEventId() {
		return eventId;
	}
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public String toString() {
		return "ParticipationViewModel{" +
				"eventId=" + eventId +
				", userId=" + userId +
				", roleId=" + roleId +
				'}';
	}
}
