package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "authority")
	private String authority;
	
	@Column(name = "label")
	private String label;
	
	@JoinTable(name = "user_authorities",
			joinColumns = @JoinColumn(name = "authority_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Employee> employees;
	
	public Authority(String authority, String label, Set<Employee> employees) {
		this.authority = authority;
		this.label = label;
		this.employees = employees;
	}
	
	public Authority() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Set<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}
