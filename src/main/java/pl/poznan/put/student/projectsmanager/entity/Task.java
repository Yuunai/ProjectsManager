package pl.poznan.put.student.projectsmanager.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(name = "project_id")
	private Project project;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "priority")
	private int priority;
	
	@Column(name = "deadline")
	private String deadline;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	@OneToMany(mappedBy = "task", orphanRemoval = true)
	private List<Comment> comments;
	
	@JoinTable(name = "participation",
				joinColumns = @JoinColumn(name = "task_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private List<User> users;
	
	public Task() {
	}
	
	public Task(Project project, String name, String description, int priority, String deadline, String status,
	            String type, String lastUpdate, List<Comment> comments, List<User> users) {
		this.project = project;
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.deadline = deadline;
		this.status = status;
		this.type = type;
		this.lastUpdate = lastUpdate;
		this.comments = comments;
		this.users = users;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
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
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getDeadline() {
		return deadline;
	}
	
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		return "Task{" +
				"id=" + id +
				", project=" + project +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", priority=" + priority +
				", deadline='" + deadline + '\'' +
				", status='" + status + '\'' +
				", type='" + type + '\'' +
				", lastUpdate='" + lastUpdate + '\'' +
				'}';
	}
}
