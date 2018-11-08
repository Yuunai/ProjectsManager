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
	
	@JoinTable(name = "user_authorities",
			joinColumns = @JoinColumn(name = "authority_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Employee> employees;
	
	public Authority(String authority, Set<Employee> employees) {
		this.authority = authority;
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
	
	public Set<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}
