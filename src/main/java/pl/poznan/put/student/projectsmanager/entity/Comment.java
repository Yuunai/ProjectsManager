package pl.poznan.put.student.projectsmanager.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@Column(name = "comment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity = Task.class)
	@Column(name = "comment")
	private Task task;
	
	@Column(name = "comment")
	private String comment;
	
	public Comment() {
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
}
