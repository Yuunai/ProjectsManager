package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "privilege")
public class Privilege {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "privilege")
	private String privilege;
	
	@Column(name = "label")
	private String label;
	
	@JoinTable(name = "adm_role_privilege",
			joinColumns = @JoinColumn(name = "privilege_id"),
			inverseJoinColumns = @JoinColumn(name = "adm_role_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<AdministrativeRole> admRoles;
	
	public Privilege(String privilege, String label, Set<AdministrativeRole> admRoles) {
		this.privilege = privilege;
		this.label = label;
		this.admRoles = admRoles;
	}
	
	public Privilege() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPrivilege() {
		return privilege;
	}
	
	public void setPrivilege(String authority) {
		this.privilege = authority;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Set<AdministrativeRole> getAdmRoles() {
		return admRoles;
	}
	
	public void setAdmRoles(Set<AdministrativeRole> admRoles) {
		this.admRoles = admRoles;
	}
}
