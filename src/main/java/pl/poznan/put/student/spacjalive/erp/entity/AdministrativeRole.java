package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "adm_role")
public class AdministrativeRole implements Serializable {
	
	public static final int ADMIN = 1;
	public static final int MODERATOR = 2;
	public static final int TRUSTED = 3;
	public static final int USER = 4;
	public static final int OUTER_USER = 5;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "adm_role")
	private String role;
	
	@Column(name = "label")
	private String label;
	
	@JoinTable(name = "user_adm_role",
			joinColumns = @JoinColumn(name = "adm_role_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<User> users;
	
	public AdministrativeRole() {
	}
	
	public AdministrativeRole(String role, String label, Collection<User> users) {
		this.role = role;
		this.label = label;
		this.users = users;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
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
	
	@Override
	public String toString() {
		return "AdministrativeRole{" +
				"id=" + id +
				", role='" + role + '\'' +
				", label='" + label + '\'' +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		AdministrativeRole that = (AdministrativeRole) o;
		
		if (id != that.id) return false;
		if (role != null ? !role.equals(that.role) : that.role != null) return false;
		return label != null ? label.equals(that.label) : that.label == null;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (label != null ? label.hashCode() : 0);
		return result;
	}
}