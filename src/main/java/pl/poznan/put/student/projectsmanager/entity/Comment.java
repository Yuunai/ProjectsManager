package pl.poznan.put.student.projectsmanager.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity = Task.class)
	@JoinColumn(name = "task_id")
	private Task task;
	
	@Column(name = "comment")
	private String comment;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "timestamp")
	private String timestamp;
	
	public Comment() {
	}
	
	public Comment(Task task, String comment, User user, String timestamp) {
		this.task = task;
		this.comment = comment;
		this.user = user;
		this.timestamp = timestamp;
	}
	
	public Comment(Task task, String comment) {
		this.task = task;
		this.comment = comment;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Task getTask() {
		return task;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
