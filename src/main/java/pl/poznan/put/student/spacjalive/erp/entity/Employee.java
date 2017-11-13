package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

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

    @Column(name = "mario_dollars")
    private double marioDollars;

    @Column(name = "password")
    private String password;

    @Column(name = "index")
    private String index;

    @Column(name = "office_entrance")
    private boolean officeEntrance;

    @ManyToOne(cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(mappedBy = "employee")
    private List<Participation> participations;

    public Employee() {

    }

    public Employee(String firstName, String lastName, String email, String phoneNumber, double marioDollars, String password, String index, boolean officeEntrance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.marioDollars = marioDollars;
        this.password = password;
        this.index = index;
        this.officeEntrance = officeEntrance;
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public boolean isOfficeEntrance() {
        return officeEntrance;
    }

    public void setOfficeEntrance(boolean officeEntrance) {
        this.officeEntrance = officeEntrance;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    private void addParticipation(Participation participation) {
        if(participations == null) {
            participations = new ArrayList<>();
            participations.add(participation);
        } else {
            participations.add(participation);
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", marioDollars=" + marioDollars +
                ", password='" + password + '\'' +
                ", index='" + index + '\'' +
                ", officeEntrance=" + officeEntrance +
                ", position=" + position +
                ", participations=" + participations +
                '}';
    }
}
