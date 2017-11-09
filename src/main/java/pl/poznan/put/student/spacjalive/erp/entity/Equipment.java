package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipment")
public class Equipment {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    @Column(name = "comments")
    private String comments;

    @Column(name = "quantity")
    private int quantity;

    @JoinTable(name = "eq_lending",
                    joinColumns = @JoinColumn(name="equipment_id"),
                    inverseJoinColumns = @JoinColumn(name = "lending_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    List <Lending> lendings;

    public void addLending(Lending lending) {
        if(lendings == null) {
            lendings = new ArrayList<>();
            lendings.add(lending);
        } else {
            lendings.add(lending);
        }
    }

    public Equipment() {

    }

    public Equipment(String name, String state, String comments, int quantity) {
        this.name = name;
        this.state = state;
        this.comments = comments;
        this.quantity = quantity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Lending> getLendings() {
        return lendings;
    }

    public void setLendings(List<Lending> lendings) {
        this.lendings = lendings;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", comments='" + comments + '\'' +
                ", quantity=" + quantity +
                '}';
    }

}
