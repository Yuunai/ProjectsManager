package pl.poznan.put.student.projectsmanager.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	@OneToMany(mappedBy = "project")
	private List<Task> tasks;
	
	@JoinTable(name = "project_user",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private List<User> users;
	
	public Project() {
	}
	
	public Project(String name, String description, String lastUpdate, List<Task> tasks, List<User> users) {
		this.name = name;
		this.description = description;
		this.lastUpdate = lastUpdate;
		this.tasks = tasks;
		this.users = users;
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void removeTask(Task task) {
		tasks.remove(task);
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
