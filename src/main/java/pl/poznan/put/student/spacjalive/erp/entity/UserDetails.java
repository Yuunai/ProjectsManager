package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_details")
public class UserDetails {
	
	@Id
	@Column(name = "user_id")
	private int userId;
	
	@NotNull(message = "Pole nie może być puste!")
	@Size(min = 1, message = "Pole nie może być puste!")
	@Size(max = 45, message = "Długość pola nie może przekroczyć 45 znaków!")
	@Pattern(regexp = "[\\p{L}]+(\\ [\\p{L}]+)?", message = "Imie musi składać się wyłącznie ze znaków alfabetu!")
	@Column(name = "first_name")
	private String firstName;
	
	@NotNull(message = "Pole nie może być puste!")
	@Size(min = 1, message = "Pole nie może być puste!")
	@Size(max = 45, message = "Długość pola nie może przekroczyć 45 znaków!")
	@Pattern(regexp = "[\\p{L}]+(\\ [\\p{L}]+)?", message = "Imie może składać się wyłącznie ze znaków alfabetu!")
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull(message = "Pole nie może być puste!")
	@Size(min = 1, message = "Pole nie może być puste!")
	@Size(max = 15, message = "Długość pola nie może przekroczyć 15 znaków!")
	@Pattern(regexp = "\\+?([0-9]+\\ ?\\-?)+", message = "Niepoprawny format numeru!")
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "student_index")
	private String studentIndex;
	
	@Column(name = "office_entrance")
	private boolean officeEntrance;
	
	@Column(name = "car")
	private boolean car;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	public UserDetails() {
	}
	
	public UserDetails(int userId, String firstName, String lastName, String phoneNumber, String studentIndex,
	                   boolean officeEntrance, boolean car, boolean active, String lastUpdate){
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.studentIndex = studentIndex;
		this.officeEntrance = officeEntrance;
		this.car = car;
		this.active = active;
		this.lastUpdate = lastUpdate;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getStudentIndex() {
		return studentIndex;
	}
	
	public void setStudentIndex(String studentIndex) {
		this.studentIndex = studentIndex;
	}
	
	public boolean isOfficeEntrance() {
		return officeEntrance;
	}
	
	public void setOfficeEntrance(boolean officeEntrance) {
		this.officeEntrance = officeEntrance;
	}
	
	public boolean isCar() {
		return car;
	}
	
	public void setCar(boolean car) {
		this.car = car;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "UserDetails{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", studentIndex='" + studentIndex + '\'' +
				", officeEntrance=" + officeEntrance +
				", car=" + car +
				", active=" + active +
				", lastUpdate='" + lastUpdate + '\'' +
				'}';
	}
}
