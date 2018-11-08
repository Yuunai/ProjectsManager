package pl.poznan.put.student.spacjalive.erp.viewmodel;

public class ParticipationViewModel {
	
	private int eventId;
	private int employeeId;
	private int roleId;
	
	public int getEventId() {
		return eventId;
	}
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
				", employeeId=" + employeeId +
				", roleId=" + roleId +
				'}';
	}
}
