package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Size(max = 45, message = "Długość pola nie może przekroczyć 45 znaków!")
    @Pattern(regexp = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$", message = "Niepoprawny email!")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Pole nie może być puste!")
    @Size(min = 1, message = "Pole nie może być puste!")
    @Size(max = 15, message = "Długość pola nie może przekroczyć 15 znaków!")
    @Pattern(regexp = "\\+?([0-9]+\\ ?\\-?)+", message = "Niepoprawny format numeru!")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_type")
    private String userType;

    @PositiveOrZero(message = "Wartość musi być wieksza lub równa 0.")
    @Digits(integer = 10, fraction = 2,message = "Dozwolone są wyłącznie liczby!")
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
