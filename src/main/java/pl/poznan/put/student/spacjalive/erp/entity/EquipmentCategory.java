package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "equipment_category")
public class EquipmentCategory implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	public EquipmentCategory() {
	}
	
	public EquipmentCategory(String name) {
		this.name = name;
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
	
	@Override
	public String toString() {
		return "EquipmentCategory{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
