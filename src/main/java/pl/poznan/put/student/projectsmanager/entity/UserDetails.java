package pl.poznan.put.student.projectsmanager.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {
	
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
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	public UserDetails() {
	}
	
	public UserDetails(int userId, String firstName, String lastName, String phoneNumber, String lastUpdate){
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
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
				", lastUpdate='" + lastUpdate + '\'' +
				'}';
	}
}
