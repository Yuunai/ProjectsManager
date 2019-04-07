package pl.poznan.put.student.projectsmanager.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	public static final java.util.regex.Pattern passwordPattern =
			java.util.regex.Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!,.])(?=\\S+$).{8,}$");
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Pole nie może być puste!")
	@Size(min = 1, message = "Pole nie może być puste!")
	@Size(max = 128, message = "Długość pola nie może przekroczyć 128 znaków!")
	@Pattern(regexp = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$", message = "Niepoprawny email!")
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!,.])(?=\\S+$).{8,}$",
			message = "Hasło musi mieć przynajmniej jedną cyfrę, wielką literę, małą literę oraz znak specjalny. " +
					"Hasło nie może zawierać znaków białych oraz nie może być krótsze niż 8 znaków")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	@JoinTable(name = "user_adm_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "adm_role_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private List<AdministrativeRole> admRoles;
	
	@JoinTable(name = "participation",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "task_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Task> tasks;
	
	@JoinTable(name = "project_user",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "project_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Project> projects;
	
	public User() {
	
	}
	
	public User(String email, String password, boolean enabled, String lastUpdate, List<AdministrativeRole> admRoles,
	            List<Task> tasks, List<Project> projects) {
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.lastUpdate = lastUpdate;
		this.admRoles = admRoles;
		this.tasks = tasks;
		this.projects = projects;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean active) {
		this.enabled = active;
	}
	
	public String getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public List<AdministrativeRole> getAdmRoles() {
		return admRoles;
	}
	
	public void setAdmRoles(List<AdministrativeRole> admRoles) {
		this.admRoles = admRoles;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", enabled=" + enabled +
				", lastUpdate='" + lastUpdate + '\'' +
				'}';
	}
}
