package pl.poznan.put.student.spacjalive.erp.viewmodel;

import pl.poznan.put.student.spacjalive.erp.entity.Position;

public class EmployeeViewModel {
//TODO add fields that can be seen by everyone and replace original class in views

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Position position;

    private String userType;

    private float marioDollars;

    private String index;

    private boolean officeEntrance;

    public EmployeeViewModel() {

    }

    public EmployeeViewModel(int id, String firstName, String lastName, String email, String phoneNumber, Position position, String userType, float marioDollars, String index, boolean officeEntrance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.userType = userType;
        this.marioDollars = marioDollars;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public float getMarioDollars() {
        return marioDollars;
    }

    public void setMarioDollars(float marioDollars) {
        this.marioDollars = marioDollars;
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

}
