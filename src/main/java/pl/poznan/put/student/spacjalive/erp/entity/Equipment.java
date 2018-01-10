package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipment")
@NamedNativeQueries({@NamedNativeQuery(name = "getEquipmentFromGivenLending", query = "CALL select_equipment_from_given_lending(:id)", resultClass = Equipment.class),
        @NamedNativeQuery(name = "getFreeEquipment", query = "CALL select_free_equipment()", resultClass = Equipment.class)})
public class Equipment {

    //TODO add fields validations

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    @Column(name = "comments")
    private String comments;

    @JoinTable(name = "eq_lending",
                    joinColumns = @JoinColumn(name="equipment_id"),
                    inverseJoinColumns = @JoinColumn(name = "lending_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List <Lending> lendings;

    @Column(name = "last_update")
    private String lastUpdate;

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

    public Equipment(String name, String state, String comments, String lastUpdate) {
        this.name = name;
        this.state = state;
        this.comments = comments;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Lending> getLendings() {
        return lendings;
    }

    public void setLendings(List<Lending> lendings) {
        this.lendings = lendings;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", comments='" + comments + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }

}
