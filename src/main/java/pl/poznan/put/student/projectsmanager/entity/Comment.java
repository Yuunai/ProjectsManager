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
	
}
