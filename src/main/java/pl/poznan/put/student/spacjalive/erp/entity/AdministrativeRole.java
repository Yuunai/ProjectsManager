package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "adm_role")
public class AdministrativeRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "label")
	String label;
	
	@JoinTable(name = "user_adm_role",
			joinColumns = @JoinColumn(name = "adm_role_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<User> users;
	
	@ManyToMany
	@JoinTable(name = "adm_role_privilege",
			joinColumns = @JoinColumn(
					name = "adm_role_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "privilege_id", referencedColumnName = "id"))
	private Collection<Privilege> privileges;
	
	public AdministrativeRole() {
	}
	
	public AdministrativeRole(String label, Collection<User> users, Collection<Privilege> privileges) {
		this.label = label;
		this.users = users;
		this.privileges = privileges;
	}
	
	public AdministrativeRole(Collection<User> users, Collection<Privilege> privileges) {
		this.users = users;
		this.privileges = privileges;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Collection<User> getUsers() {
		return users;
	}
	
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	public Collection<Privilege> getPrivileges() {
		return privileges;
	}
	
	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	@Override
	public String toString() {
		return "AdministrativeRole{" +
				"id=" + id +
				", label='" + label + '\'' +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		AdministrativeRole that = (AdministrativeRole) o;
		
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		return label != null ? label.equals(that.label) : that.label == null;
	}
	
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (label != null ? label.hashCode() : 0);
		return result;
	}
}