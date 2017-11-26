package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    //TODO add fields validations

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "mario_dollars")
    private double marioDollars;

    @Column(name = "password")
    private String password;

    @Column(name = "student_index")
    private String studentIndex;

    @Column(name = "office_entrance")
    private int officeEntrance;

    @Column(name = "active")
    private int active;

    @ManyToOne(cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinColumn(name = "position_id")
    private Position position;

    public Employee() {

    }

    public Employee(String firstName, String lastName, String email, String phoneNumber, String userType, double marioDollars, String password, String studentIndex, int officeEntrance, int active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.marioDollars = marioDollars;
        this.password = password;
        this.studentIndex = studentIndex;
        this.officeEntrance = officeEntrance;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public double getMarioDollars() {
        return marioDollars;
    }

    public void setMarioDollars(double marioDollars) {
        this.marioDollars = marioDollars;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }

    public int getOfficeEntrance() {
        return officeEntrance;
    }

    public void setOfficeEntrance(int officeEntrance) {
        this.officeEntrance = officeEntrance;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType='" + userType + '\'' +
                ", marioDollars=" + marioDollars +
                ", password='" + password + '\'' +
                ", studentIndex='" + studentIndex + '\'' +
                ", officeEntrance=" + officeEntrance +
                ", active=" + active +
                ", position=" + position +
                '}';
    }
}
