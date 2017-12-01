package pl.poznan.put.student.spacjalive.erp.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Immutable
@Entity
@Table(name = "most_active_employees")
public class MostActiveEmployeeView {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "mario_dollars")
    double marioDollars;

    public MostActiveEmployeeView() {

    }

    public MostActiveEmployeeView(String firstName, String lastName, double marioDollars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.marioDollars = marioDollars;
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

    public double getMarioDollars() {
        return marioDollars;
    }

    public void setMarioDollars(double marioDollars) {
        this.marioDollars = marioDollars;
    }

    @Override
    public String toString() {
        return "MostActiveEmployeeView{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", marioDollars=" + marioDollars +
                '}';
    }
}
